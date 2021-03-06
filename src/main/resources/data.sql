INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)
VALUES ('123 Maple St','London','On', 'N1N-1N1','(555)555-5555','Trusted','ABC Supply Co.','abc@supply.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)
VALUES ('543 Sycamore Ave','Toronto','On', 'N1P-1N1','(999)555-5555','Trusted','Big Bills Depot','bb@depot.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)
VALUES ('922 Oak St','London','On', 'N1N-1N1','(555)555-5599','Untrusted','Shady Sams','ss@underthetable.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)
VALUES ('1601 Richmond St','London','On', 'N7K-3W5','(519)666-6978','Untrusted','Tianyi Li','tyl@gmail.com');
-- add Product
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('bdi001', 1, 'Bacardi', 19.99, 24.99, 5, 2, 20, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('jd001', 1, 'Jack Daniels', 29.99, 39.99, 10, 3, 15, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('hn001', 1, 'Hennessy', 69.99, 99.99, 5, 4, 15, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('jw001', 1, 'Johnnie Walker', 19.99, 29.99, 20, 6, 15, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('rb001', 2, 'Root Beer', 2.99, 5.99, 15, 3, 20, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('up001', 2, '7UP', 2.99, 5.99, 20, 4, 20, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('pp001', 2, 'Pepsi', 1.99, 4.99, 25, 5, 30, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('lj001', 2, 'Lemon Juice', 2.99, 5.99, 30, 12, 30, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('hb001', 3, 'Coffee Maker', 119.99, 159.99, 5, 16, 3, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('dl001', 3, 'DeLonghi Espresso', 169.99, 229.99, 5, 12, 6, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('hb101', 3, 'Bread Maker', 79.99, 109.99, 10, 17, 9, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('bs001', 4, 'Vacuum', 139.99, 209.99, 5, 20, 5, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('bs002', 4, 'Vacuum Pro', 159.99, 259.99, 10, 30, 6, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('ds001', 4, 'Dyson', 349.99, 459.99, 10, 40, 3, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('rbv001', 4, 'Robot Vacuum', 200.00, 349.99, 6, 27, 7, 0, '', '');
INSERT INTO Product (ID, VendorId, Name, CostPrice, MSRP, ROP, EOQ, QOH, QOO, QRCode, QRCodeTxt)
VALUES ('bs101', 4, 'Stick Vacuum', 35.99, 59.99, 6, 11, 7, 0, '', '');
