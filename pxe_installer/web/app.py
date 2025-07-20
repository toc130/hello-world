from flask import Flask, render_template, request, redirect, url_for, flash
import sys
import os

# Add the parent directory to the Python path
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from pxe_manager import PXEManager

app = Flask(__name__)
app.secret_key = 'supersecretkey'
pxe_manager = PXEManager(db_file='../tftpboot/pxelinux.cfg/host_db.json',
                         tftp_config_path='../tftpboot/pxelinux.cfg/',
                         web_server_ip='192.168.1.100') # Replace with your server's IP

@app.route('/')
def index():
    hosts = pxe_manager.list_hosts()
    return render_template('index.html', hosts=hosts)

@app.route('/add', methods=['POST'])
def add_host():
    mac = request.form.get('mac')
    os_choice = request.form.get('os')
    if mac and os_choice:
        try:
            pxe_manager.add_host(mac, os_choice)
            flash(f'Host {mac} added successfully for {os_choice}.', 'success')
        except ValueError as e:
            flash(str(e), 'danger')
    else:
        flash('MAC address and OS are required.', 'danger')
    return redirect(url_for('index'))

@app.route('/remove', methods=['POST'])
def remove_host():
    mac = request.form.get('mac')
    if mac:
        pxe_manager.remove_host(mac)
        flash(f'Host {mac} removed successfully.', 'success')
    return redirect(url_for('index'))

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001, debug=True)
