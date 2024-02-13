SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`
(
    `ID`           int(11) NOT NULL AUTO_INCREMENT,
    `ORDER_NO`     varchar(255)   DEFAULT NULL,
    `ACCOUNT_CODE` varchar(255)   DEFAULT NULL,
    `PRODUCT_CODE` varchar(255)   DEFAULT NULL,
    `COUNT`        int(11)        DEFAULT '0',
    `AMOUNT`       decimal(10, 2) DEFAULT '0.00',
    PRIMARY KEY (`ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`
(
    `ID`           int(11) NOT NULL AUTO_INCREMENT,
    `PRODUCT_CODE` varchar(255)   DEFAULT NULL COMMENT '编码',
    `PRODUCT_NAME` varchar(255)   DEFAULT NULL COMMENT '名称',
    `COUNT`        int(11)        DEFAULT '0' COMMENT '库存数量',
    `PRICE`        decimal(10, 2) DEFAULT '0.00' COMMENT '单价',
    PRIMARY KEY (`ID`),
    UNIQUE KEY `UK_PRODUCT_CODE` (`PRODUCT_CODE`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product`
VALUES ('1', 'P001', '笔记本', '10', '3000.00');
INSERT INTO `product`
VALUES ('2', 'P002', '手表', '5', '250.00');
INSERT INTO `product`
VALUES ('3', 'P003', '键盘', '50', '100.00');
INSERT INTO `product`
VALUES ('4', 'P004', '辣条', '1000', '0.50');

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`
(
    `ID`           int(11) NOT NULL AUTO_INCREMENT,
    `ACCOUNT_CODE` varchar(255)   DEFAULT NULL,
    `ACCOUNT_NAME` varchar(255)   DEFAULT NULL,
    `AMOUNT`       decimal(10, 2) DEFAULT '0.00',
    PRIMARY KEY (`ID`),
    UNIQUE KEY `UK_ACCOUNT_CODE` (`ACCOUNT_CODE`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account`
VALUES ('1', 'hjl', 'hujiale', '10000.00');