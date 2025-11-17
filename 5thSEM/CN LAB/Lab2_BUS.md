Here’s your **Lab Record Content for “Design a Bus Topology Network using Switches”** — written neatly and in the same consistent format as your other labs 👇

---

## **Lab: Design of a Bus Topology Network using Switches**

### **Aim:**

To design a Bus topology network using switches and PCs, and to verify connectivity among all devices.

---

### **Objectives:**

1. To design a **Bus Topology** using **switches** and **five PCs**.
2. To assign IP addresses to all PCs in the same network.
3. To verify the connectivity between all devices.

---

### **Addressing Table:**

| **Device** | **Interface** | **IP Address** | **Subnet Mask** |
| ---------- | ------------- | -------------- | --------------- |
| PC1        | NIC           | 192.172.1.2    | 255.255.255.0   |
| PC2        | NIC           | 192.172.1.3    | 255.255.255.0   |
| PC3        | NIC           | 192.172.1.4    | 255.255.255.0   |
| PC4        | NIC           | 192.172.1.5    | 255.255.255.0   |
| PC5        | NIC           | 192.172.1.6    | 255.255.255.0   |

---

### **Brief Procedure:**

1. **Design the topology:**
   Arrange the switches in a linear (bus) manner and connect the PCs to the switches using straight-through cables.

2. **Assign IP addresses:**
   Configure IP addresses on all PCs as per the addressing table, ensuring all are in the same subnet.

3. **Verify connectivity:**
   Use the `ping` command from one PC to all others to ensure successful communication.
   Example:

   ```
   ping 192.172.1.3
   ping 192.172.1.4
   ping 192.172.1.5
   ping 192.172.1.6
   ```

---

### **Observations:**

* All PCs were configured within the same network range.
* Successful ping replies were received between all PCs, confirming network connectivity.

---

### **Conclusion:**

A **Bus topology** network was successfully designed using switches and PCs.
All devices communicated effectively, confirming proper configuration and functional connectivity within the network.

---

Would you like me to name this as **Lab 2** (to maintain sequence) or keep it as a separate **topology experiment**?
