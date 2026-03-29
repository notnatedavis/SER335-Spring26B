#!/usr/bin/env python3

from scapy.all import *
import sys

filterCriteria= sys.argv[1]

def print_pkt(pkt) :
	pkt.show()
pkt = sniff(iface='enp2s0', filter= filterCriteria, prn=print_pkt)