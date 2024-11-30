#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import re
import subprocess
import sys
import ipaddress
from concurrent.futures import ThreadPoolExecutor
import time

def is_valid_ip(ip_str):
    """验证IP地址格式是否正确"""
    try:
        ipaddress.ip_address(ip_str)
        return True
    except ValueError:
        return False

def ping_ip(ip):
    """使用ping命令检测IP是否可达"""
    try:
        # macOS 下 ping 命令使用 -c 参数指定次数
        result = subprocess.run(['ping', '-c', '1', '-W', '1', ip], 
                              capture_output=True, text=True)
        is_reachable = result.returncode == 0
        return {
            'ip': ip,
            'ping_status': 'reachable' if is_reachable else 'unreachable',
            'ping_output': result.stdout
        }
    except Exception as e:
        return {
            'ip': ip,
            'ping_status': 'error',
            'ping_output': str(e)
        }

def arping_ip(ip):
    """使用arping命令检测IP的MAC地址"""
    try:
        # 注意：arping 命令需要root权限
        result = subprocess.run(['sudo', 'arping', '-c', '1', '-w', '1', ip],
                              capture_output=True, text=True)
        has_response = result.returncode == 0
        return {
            'ip': ip,
            'arp_status': 'responded' if has_response else 'no_response',
            'arp_output': result.stdout
        }
    except Exception as e:
        return {
            'ip': ip,
            'arp_status': 'error',
            'arp_output': str(e)
        }

def process_file(filename):
    """处理文件中的IP地址"""
    # IP地址的正则表达式模式
    ip_pattern = r'\b(?:\d{1,3}\.){3}\d{1,3}\b'
    
    try:
        with open(filename, 'r') as file:
            content = file.read()
    except FileNotFoundError:
        print(f"错误：找不到文件 {filename}")
        return
    except Exception as e:
        print(f"读取文件时出错：{e}")
        return

    # 找出所有IP地址
    ip_addresses = set(re.findall(ip_pattern, content))
    if not ip_addresses:
        print("文件中没有找到IP地址")
        return

    print(f"找到 {len(ip_addresses)} 个唯一IP地址")
    
    # 验证IP地址格式
    valid_ips = [ip for ip in ip_addresses if is_valid_ip(ip)]
    print(f"其中 {len(valid_ips)} 个IP地址格式有效")

    # 使用线程池并行处理ping和arping
    with ThreadPoolExecutor(max_workers=10) as executor:
        # 并行执行ping
        ping_results = list(executor.map(ping_ip, valid_ips))
        # 并行执行arping
        arp_results = list(executor.map(arping_ip, valid_ips))

    # 合并结果并输出
    for ip in valid_ips:
        print("\n" + "="*50)
        print(f"IP地址: {ip}")
        
        # 查找该IP的ping结果
        ping_result = next((r for r in ping_results if r['ip'] == ip), None)
        if ping_result:
            print(f"Ping状态: {ping_result['ping_status']}")
            if ping_result['ping_status'] == 'reachable':
                print("Ping成功")
            else:
                print("Ping失败")

        # 查找该IP的arping结果
        arp_result = next((r for r in arp_results if r['ip'] == ip), None)
        if arp_result:
            print(f"ARP状态: {arp_result['arp_status']}")
            if arp_result['arp_status'] == 'responded':
                print("ARP响应成功")
            else:
                print("ARP响应失败")

def main():
    if len(sys.argv) != 2:
        print("使用方法: python ip_validator.py <文件名>")
        sys.exit(1)
    
    filename = sys.argv[1]
    process_file(filename)

if __name__ == "__main__":
    main()
