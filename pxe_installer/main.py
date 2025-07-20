import argparse
from pxe_manager import PXEManager

def main():
    parser = argparse.ArgumentParser(description="PXE Host Management CLI")
    subparsers = parser.add_subparsers(dest="command", required=True)

    # Add host command
    parser_add = subparsers.add_parser("add-host", help="Add a new host to the PXE configuration.")
    parser_add.add_argument("--mac", required=True, help="MAC address of the host.")
    parser_add.add_argument("--os", required=True, help="Operating system to assign (e.g., centos7).")

    # Remove host command
    parser_remove = subparsers.add_parser("remove-host", help="Remove a host from the PXE configuration.")
    parser_remove.add_argument("--mac", required=True, help="MAC address of the host.")

    # List hosts command
    subparsers.add_parser("list-hosts", help="List all registered hosts.")

    args = parser.parse_args()

    pxe_manager = PXEManager()

    if args.command == "add-host":
        try:
            pxe_manager.add_host(args.mac, args.os)
            print(f"Host {args.mac} added successfully for {args.os}.")
        except ValueError as e:
            print(f"Error: {e}")
    elif args.command == "remove-host":
        pxe_manager.remove_host(args.mac)
        print(f"Host {args.mac} removed successfully.")
    elif args.command == "list-hosts":
        hosts = pxe_manager.list_hosts()
        if hosts:
            for host in hosts:
                print(f"MAC: {host['mac']}, OS: {host['os']}")
        else:
            print("No hosts are registered.")

if __name__ == "__main__":
    main()
