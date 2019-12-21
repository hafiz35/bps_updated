drop database billpaymentsystem;

create database billpaymentsystem;

use billpaymentsystem;


INSERT INTO `billpaymentsystem`.`role`(`ro_id`,`ro_name`)
VALUES(1,'ROLE_ADMIN'),
	  (2,'ROLE_USER'),
      (3,'ROLE_VENDOR');

INSERT INTO `billpaymentsystem`.`payment_gateway`(`pg_id`,`pg_name`)
VALUES(1,'Paytm'),
	  (2,'Card'),
	  (3,'AmazonPay');

INSERT INTO `billpaymentsystem`.`vendor_type`(`vt_id`,`vt_image`,`vt_name`)
VALUES(1,'live_tv','DTH'),
	  (2,'flash_on','Electricity'),
      (3, 'phone_android', 'Telephone'),
      (4, 'assignment_ind', 'Insurance'),
      (5, 'opacity', 'Water'),
      (6, 'credit_card', 'Credit Card'),
      (7, 'account_balance', 'Loan Account');
      
SELECT * FROM billpaymentsystem.bill;

SELECT * FROM billpaymentsystem.issue;

SELECT * FROM billpaymentsystem.payment_gateway;

SELECT * FROM billpaymentsystem.role;

SELECT * FROM billpaymentsystem.user;

SELECT * FROM billpaymentsystem.user_has_vendor;

SELECT * FROM billpaymentsystem.vendor;

SELECT * FROM billpaymentsystem.vendor_has_payment_gateway;

SELECT * FROM billpaymentsystem.vendor_type;