import json
import os
import re

class PXEManager:
    def __init__(self, db_file='tftpboot/pxelinux.cfg/host_db.json',
                 tftp_config_path='tftpboot/pxelinux.cfg/',
                 web_server_ip='127.0.0.1'):
        self.db_file = db_file
        self.tftp_config_path = tftp_config_path
        self.web_server_ip = web_server_ip
        self.hosts = self._load_db()

    def _load_db(self):
        if not os.path.exists(self.db_file):
            return {}
        with open(self.db_file, 'r') as f:
            try:
                return json.load(f)
            except json.JSONDecodeError:
                return {}

    def _save_db(self):
        with open(self.db_file, 'w') as f:
            json.dump(self.hosts, f, indent=4)

    def _generate_pxe_config(self, mac, os_choice):
        mac_filename = "01-" + mac.replace(":", "-")
        config_path = os.path.join(self.tftp_config_path, mac_filename)

        if os_choice == "centos7":
            kernel_path = f"/os_images/centos7/vmlinuz"
            initrd_path = f"/os_images/centos7/initrd.img"
            append_line = f"initrd={initrd_path} inst.ks=http://{self.web_server_ip}/os_images/centos7/ks.cfg"
        elif os_choice == "ubuntu20.04":
            kernel_path = f"/os_images/ubuntu20.04/vmlinuz"
            initrd_path = f"/os_images/ubuntu20.04/initrd.gz"
            append_line = f"initrd={initrd_path} auto=true priority=critical url=http://{self.web_server_ip}/os_images/ubuntu20.04/preseed.cfg"
        else:
            raise ValueError("Unsupported OS choice.")

        config_content = f"""
DEFAULT {os_choice}
LABEL {os_choice}
    MENU LABEL Install {os_choice}
    KERNEL {kernel_path}
    APPEND {append_line}
"""
        with open(config_path, 'w') as f:
            f.write(config_content)

    def add_host(self, mac, os_choice):
        mac = mac.lower()
        if not re.match("[0-9a-f]{2}([-:]?)[0-9a-f]{2}(\\1[0-9a-f]{2}){4}$", mac):
            raise ValueError("Invalid MAC address format.")

        if mac in self.hosts:
            raise ValueError(f"Host with MAC {mac} already exists.")

        self.hosts[mac] = {"os": os_choice}
        self._generate_pxe_config(mac, os_choice)
        self._save_db()

    def remove_host(self, mac):
        mac = mac.lower()
        if mac in self.hosts:
            del self.hosts[mac]
            mac_filename = "01-" + mac.replace(":", "-")
            config_path = os.path.join(self.tftp_config_path, mac_filename)
            if os.path.exists(config_path):
                os.remove(config_path)
            self._save_db()

    def list_hosts(self):
        return [{"mac": mac, "os": data["os"]} for mac, data in self.hosts.items()]
