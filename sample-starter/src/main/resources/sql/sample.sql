#todo 你的建表语句,包含索引
CREATE TABLE `order`  (
     `id` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '订单号',
     `userid` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '购买人',
     `skuid` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '商品规格id',
     `amount` int(11) NULL DEFAULT NULL COMMENT '购买数量',
     `money` decimal(10, 0) NULL DEFAULT NULL COMMENT '购买金额',
     `paystatus` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '支付状态',
     `delflag` bigint(20) NULL DEFAULT NULL COMMENT '删除标志',
     `createby` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '创建人',
     `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
     `updateBy` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '修改人',
     `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE INDEX `orderIndex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;