#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import subprocess
import sys
import re
import socket
import time
import platform

def is_windows():
    return platform.system().lower() == "windows"

def get_network_prefix():
    """获取本机网络前缀"""
    try:
        if is_windows():
            cmd = ['ipconfig']
        else:
            cmd = ['ifconfig']
        
        result = subprocess.run(cmd, capture_output=True, text=True)
        # 查找类似 192.168.1. 的网络前缀
        matches = re.findall(r'(\d{1,3}\.\d{1,3}\.\d{1,3}\.)', result.stdout)
        if matches:
            return matches[0]
    except Exception:
        pass
    return None

def get_mac_from_arp(ip):
    """从ARP缓存中获取MAC地址"""
    try:
        if is_windows():
            result = subprocess.run(['arp', '-a', ip], capture_output=True, text=True)
        else:
            result = subprocess.run(['arp', '-n', ip], capture_output=True, text=True)
        
        mac_match = re.search(r'([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})', result.stdout)
        return mac_match.group(0) if mac_match else None
    except Exception:
        return None

def clear_arp_cache(ip):
    """清除特定IP的ARP缓存"""
    try:
        if is_windows():
            subprocess.run(['arp', '-d', ip], capture_output=True)
        else:
            subprocess.run(['sudo', 'arp', '-d', ip], capture_output=True)
    except Exception:
        pass

def check_ip_conflict(ip):
    """检查IP地址是否存在冲突"""
    results = []
    
    # 1. 清除ARP缓存以确保获取最新状态
    print(f"正在清除 {ip} 的ARP缓存...")
    clear_arp_cache(ip)
    time.sleep(1)  # 等待缓存清除
    
    # 2. 进行 ping 测试
    print(f"正在 ping {ip}...")
    try:
        if is_windows():
            ping_cmd = ['ping', '-n', '1', '-w', '1000', ip]
        else:
            ping_cmd = ['ping', '-c', '1', '-W', '1', ip]
        
        ping_result = subprocess.run(ping_cmd, capture_output=True, text=True)
        is_reachable = ping_result.returncode == 0
        
        if is_reachable:
            # 3. 如果能 ping 通，检查 ARP 缓存中的 MAC 地址
            print("正在检查 ARP 缓存...")
            time.sleep(1)  # 等待ARP缓存更新
            mac = get_mac_from_arp(ip)
            if mac:
                results.append(f"IP {ip} 已被使用，对应的MAC地址为: {mac}")
                return results
            else:
                results.append(f"IP {ip} 响应 ping，但无法获取MAC地址")
                return results
    except Exception as e:
        results.append(f"Ping 检测出错: {str(e)}")
    
    # 4. 尝试发送ARP请求
    try:
        print("正在发送 ARP 请求...")
        if not is_windows():  # macOS/Linux
            arp_cmd = ['arping', '-c', '2', '-w', '2', ip]
        else:  # Windows
            arp_cmd = ['arp', '-a', ip]
        
        arp_result = subprocess.run(arp_cmd, capture_output=True, text=True)
        if "Received" in arp_result.stdout and "1 response" in arp_result.stdout:
            results.append(f"IP {ip} 响应 ARP 请求，地址已被使用")
            return results
    except FileNotFoundError:
        print("arping 命令不可用，跳过 ARP 检测")
    except Exception as e:
        print(f"ARP 检测出错: {str(e)}")
    
    # 5. 尝试绑定端口
    try:
        print("正在尝试绑定端口...")
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.settimeout(1)
        sock.bind((ip, 0))
        sock.close()
        # 即使能绑定端口，也要确认是否真的没被使用
        if not any("已被使用" in r for r in results):
            results.append(f"IP {ip} 当前可能未被使用")
    except socket.error:
        if not results:
            results.append(f"IP {ip} 无法绑定端口，可能已被使用")
    
    # 6. 检查是否在同一网段
    network_prefix = get_network_prefix()
    if network_prefix and not ip.startswith(network_prefix):
        results.append(f"警告：IP {ip} 不在当前网段 {network_prefix}0/24 内")
    
    return results

def main():
    if len(sys.argv) != 2:
        print("使用方法: python ip_conflict_checker.py <IP地址>")
        print("示例: python ip_conflict_checker.py 192.168.1.100")
        sys.exit(1)
    
    ip = sys.argv[1]
    print(f"正在检查IP地址 {ip} 是否存在冲突...")
    print("-" * 50)
    
    results = check_ip_conflict(ip)
    for result in results:
        print(result)
    
    print("-" * 50)
    print("建议:")
    print("1. 如果发现IP冲突，请更换一个不同的IP地址")
    print("2. 使用DHCP自动分配IP地址可以避免冲突")
    print("3. 在网络管理员的帮助下解决IP冲突问题")

if __name__ == "__main__":
    main()
