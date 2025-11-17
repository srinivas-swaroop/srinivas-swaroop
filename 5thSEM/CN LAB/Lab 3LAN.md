Here’s your **Lab Record Content for Lab 3** — neat, short, and in the same consistent format 👇

---

## **Lab 3: Design of a LAN Network using a Single Switch**

### **Aim:**

To design and configure a Local Area Network (LAN) using a single switch connecting four PCs and verify network connectivity.

---

### **Objectives:**

1. To design a LAN using one switch and four PCs.
2. To assign IP addresses to all PCs.
3. To verify the connectivity among all PCs within the LAN.

---

### **Addressing Table:**

| **Device** | **Interface** | **IP Address** | **Subnet Mask** |
| ---------- | ------------- | -------------- | --------------- |
| PC1        | NIC           | 192.172.1.2    | 255.255.255.0   |
| PC2        | NIC           | 192.172.1.3    | 255.255.255.0   |
| PC3        | NIC           | 192.172.1.4    | 255.255.255.0   |
| PC4        | NIC           | 192.172.1.5    | 255.255.255.0   |

---

### **Brief Procedure:**

1. **Design the topology:**
   Connect all four PCs to a single switch using straight-through cables.

2. **Assign IP addresses:**
   Configure IP addresses manually on each PC as per the addressing table.

3. **Verify connectivity:**
   Use the `ping` command to test connectivity between all PCs.
   For example:

   ```
   ping 192.172.1.3
   ping 192.172.1.4
   ping 192.172.1.5
   ```

---

### **Observations:**

* All PCs were assigned IP addresses in the same network.
* Ping results between all PCs were successful, confirming LAN communication.

---

### **Conclusion:**

A LAN was successfully designed and configured using a single switch connecting four PCs.
All systems within the network communicated effectively, verifying proper LAN setup.

---

Would you like me to make **Lab 5** next in the same uniform style?
