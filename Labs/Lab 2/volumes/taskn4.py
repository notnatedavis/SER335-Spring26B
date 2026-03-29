# Labs/Lab 2/taskn4.py
# Task N4
# SER335
# Spring '26B
# Nathaniel Davis-Perez
 
# ------ Imports ------

from scapy.all import *

# ----- Main ----- 

def spoof_pkt(pkt) :
    # callback func : called for every captured ICMP echo Request
    # sends spoofed ICMP echo reply to original sender

    # only process ICMP echo request packets
    if ICMP in pkt and pkt[ICMP].type == 8 :  # type 8 = echo request
        # extract source & destination IPs from the request
        original_src = pkt[IP].src
        original_dst = pkt[IP].dst

        # build reply packet
        ip = IP(src=original_dst, dst=original_src) # swap source & destination
        icmp = ICMP(type=0, id=pkt[ICMP].id, seq=pkt[ICMP].seq)  # type 0 = Echo Reply
        
        # copy the payload (data) from the request to the reply (if exists)
        data = pkt[Raw].load if Raw in pkt else b''
        reply = ip / icmp / data

        # send the spoofed reply
        send(reply, verbose=False)
        print(f"Spoofed reply to {original_src} for {original_dst}")

# start sniffing on interface carrying traffic (enp2s0)
# sniff all ICMP packets and call spoof_pkt for each
sniff(iface='enp2s0', filter='icmp', prn=spoof_pkt)