DROP TABLE IF EXISTS TRV01MT;
DROP TABLE IF EXISTS TPT01HT;
DROP TABLE IF EXISTS TPT01MT;
DROP TABLE IF EXISTS TMB01MT;

CREATE TABLE `TMB01MT` (
    `userId`	BINARY(16)	NOT NULL
);

CREATE TABLE `TPT01HT` (
                           `pointId`	BINARY(16)	NOT NULL	COMMENT '포인트ID',
                           `userId`	BINARY(16)	NOT NULL	COMMENT '유저Id',
                           `pointType`	VARCHAR(6)	NOT NULL	COMMENT '포인트 적립 유형',
                           `serviceId`	BINARY(16)	NOT NULL	COMMENT '포인트 발생 서비스Id',
                           `pointStatus`	VARCHAR(6)	NOT NULL	COMMENT '포인트 상태(적립,반환)',
                           `point`	INT(3)	NOT NULL	COMMENT '포인트 값',
                           `regDate`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '포인트 발생일자'
);

CREATE TABLE `TRV01MT` (
                           `reviewId`	BINARY(16)	NOT NULL	COMMENT '리뷰ID',
                           `content`	VARCHAR(500)	NOT NULL	COMMENT '리뷰컨텐츠',
                           `regDate`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '리뷰작성일',
                           `attachedPhotoIds`	VARCHAR(200)	NULL	COMMENT '리뷰 첨부파일ID',
                           `userId`	BINARY(16)	NOT NULL	COMMENT '리뷰작성회원ID',
                           `placeId`	BINARY(16)	NOT NULL	COMMENT '리뷰작성장소',
                           `updDate`	DATETIME	NULL	COMMENT '리뷰수정일자'
);

CREATE TABLE `TPT01MT` (
                           `totalPointId`	BINARY(16)	NOT NULL,
                           `userId`	BINARY(16)	NOT NULL,
                           `totalPoint`	INT	NOT NULL	COMMENT '총 포인트',
                           `updDate`	DATETIME	NULL	COMMENT '포인트갱신일자',
                           `regDate`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '포인트 발생일자'
);

ALTER TABLE `TMB01MT` ADD CONSTRAINT `PK_TMB01MT` PRIMARY KEY (
                                                               `userId`
    );

ALTER TABLE `TPT01HT` ADD CONSTRAINT `PK_TPT01HT` PRIMARY KEY (
                                                               `pointId`
    );

ALTER TABLE `TRV01MT` ADD CONSTRAINT `PK_TRV01MT` PRIMARY KEY (
                                                               `reviewId`
    );

ALTER TABLE `TPT01MT` ADD CONSTRAINT `PK_TPT01MT` PRIMARY KEY (
                                                               `totalPointId`
    );

ALTER TABLE `TPT01HT` ADD CONSTRAINT `FK_TMB01MT_TO_TPT01HT_1` FOREIGN KEY (
                                                                            `userId`
    )
    REFERENCES `TMB01MT` (
                          `userId`
        );

ALTER TABLE `TRV01MT` ADD CONSTRAINT `FK_TMB01MT_TO_TRV01MT_1` FOREIGN KEY (
                                                                            `userId`
    )
    REFERENCES `TMB01MT` (
                          `userId`
        );

ALTER TABLE `TPT01MT` ADD CONSTRAINT `FK_TMB01MT_TO_TPT01MT_1` FOREIGN KEY (
                                                                            `userId`
    )
    REFERENCES `TMB01MT` (
                          `userId`
        );


-- CREATE TABLE `TMB01MT` (
--                            `userId` binary(16) NOT NULL COMMENT '포인트ID',
--                            PRIMARY KEY (`userId`)
-- )

-- CREATE TABLE `TPT01HT` (
--                            `pointId` binary(16) NOT NULL COMMENT '포인트ID',
--                            `userId` binary(16) NOT NULL COMMENT '유저Id',
--                            `pointType` varchar(6) NOT NULL COMMENT '포인트 적립 유형',
--                            `serviceId` binary(16) NOT NULL COMMENT '포인트 발생 서비스Id',
--                            `pointStatus` varchar(6)  NOT NULL COMMENT '포인트 상태(적립,반환)',
--                            `point` int NOT NULL COMMENT '포인트 값',
--                            `regDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '포인트 발생일자',
--                            PRIMARY KEY (`pointId`),
--                            KEY `FK_TMB01MT_TO_TPT01HT_1` (`userId`),
--                            CONSTRAINT `FK_TMB01MT_TO_TPT01HT_1` FOREIGN KEY (`userId`) REFERENCES `TMB01MT` (`userId`)
-- )

-- CREATE TABLE `TPT01MT` (
--                            `totalPointId` binary(16) NOT NULL,
--                            `userId` binary(16) NOT NULL,
--                            `totalPoint` int NOT NULL COMMENT '총 포인트',
--                            `updDate` datetime DEFAULT NULL COMMENT '포인트갱신일자',
--                            `regDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '포인트 발생일자',
--                            PRIMARY KEY (`totalPointId`),
--                            KEY `FK_TMB01MT_TO_TPT01MT_1` (`userId`),
--                            CONSTRAINT `FK_TMB01MT_TO_TPT01MT_1` FOREIGN KEY (`userId`) REFERENCES `TMB01MT` (`userId`)
-- )
--
-- CREATE TABLE `TRV01MT` (
--                            `reviewId` binary(16) NOT NULL COMMENT '리뷰ID',
--                            `content` varchar(500) NOT NULL COMMENT '리뷰컨텐츠',
--                            `regDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '리뷰작성일',
--                            `attachedPhotoIds` varchar(200) DEFAULT NULL COMMENT '리뷰 첨부파일ID',
--                            `userId` binary(16) NOT NULL COMMENT '리뷰작성회원ID',
--                            `placeId` binary(16) NOT NULL COMMENT '리뷰작성장소',
--                            `updDate` datetime DEFAULT NULL COMMENT '리뷰수정일자',
--                            PRIMARY KEY (`reviewId`),
--                            KEY `FK_TMB01MT_TO_TRV01MT_1` (`userId`),
--                            CONSTRAINT `FK_TMB01MT_TO_TRV01MT_1` FOREIGN KEY (`userId`) REFERENCES `TMB01MT` (`userId`)
-- )