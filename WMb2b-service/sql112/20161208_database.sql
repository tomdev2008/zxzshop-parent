-- 二期数据结构改动脚本
-- 分类添加字段
alter table wm_productcategory_t add column IsIndexShow bit(1) default 0 comment '是否前端展示,默认不显示为0' AFTER DisplayOrder;

-- 商品添加规格
alter table wm_product_t add column Sku varchar(255) comment '规格';

-- 询价添加型号
alter table wm_inquirysheetproduct_t add column  Model varchar(255) comment '型号';
 
-- 采购添加规格
alter table wm_purchaseproduct_t add COLUMN Sku varchar(255) comment '规格';
 
-- 报价添加字段是否查看
ALTER  table wm_quotestatistic_t add column  IsView   bit(1) comment '是否查看';

-- 询价商品添加企业字段
alter table wm_inquirysheetproduct_t add column CompanyId bigint default null comment '询价商品的来源企业';

-- 采购商品添加企业字段
alter table wm_purchaseproduct_t add column CompanyId bigint default null comment '采购商品的来源企业';

-- 貨物自取時添加自取的聯系人,方式,地址
alter table wm_ordertransfer_t  add column ShipperContact  varchar(64) comment '（自取时）取货联系人';
alter table wm_ordertransfer_t  add column ShipperPhone  varchar(32) comment '（自取时）取货联系电话';
alter table wm_ordertransfer_t  add column ShipperAdd  varchar(256) comment '(自取时) 取货地址';
   
-- 添加报价汇总表的类型数据
alter table wm_quotestatistic_t add column QuoteType tinyint default 0 not null comment '报价类型 1-询价 2-采购';

-- 报价添加截止字段
ALTER TABLE wm_quotestatistic_t ADD COLUMN QuoteEndTime datetime default null comment '报价的有效时间';

-- 添加购物车表
drop table if exists wm_productcar_t;
create table wm_productcar_t
(
   id                   bigint not null auto_increment comment '表id',
   userId               bigint comment '用户id',
   productId            bigint comment '产品id',
   enterpriseInfoId     bigint comment '企业id',
   addCount             int(11) comment '采购数量',
   primary key (id)
);
alter table wm_productcar_t comment '购物车';

ALTER TABLE wm_sys_power ADD COLUMN source_type INT(11) COMMENT '权限资源类型(0-功能菜单 1-页面按钮 2-数据资源)' AFTER sta;
ALTER TABLE wm_sys_power ADD COLUMN owner_sys VARCHAR(50) NULL COMMENT '所属系统' AFTER source_type;

-- 添加协议表扩展字段
ALTER TABLE wm_protocolextra_t ADD (RecognizeCode varchar(32) default '',CompanyPhone VARCHAR(32) default '',CompanyAddr VARCHAR(256) default '',TicketName VARCHAR(128) default null COMMENT '发票抬头');

-- 询价服务费订单加入下单用户
ALTER TABLE wm_inquiryorder_t ADD COLUMN UserId BIGINT COMMENT '下单用户';

-- 采购车增加字段
ALTER TABLE wm_productcar_t ADD COLUMN addTime datetime comment '加入购物车时间';

-- 黑名单添加操作类型
alter table `wm_blacklist_log_t` add `OperateType` tinyint not null default 1 comment '操作类型,1:加入黑名单，2：移除黑名单，3：删除用户'; 
