insert into category (category_type) VALUES ('TOTAL');
insert into category (category_type) VALUES ('GAME');
insert into category (category_type) VALUES ('BEAUTY');
insert into category (category_type) VALUES ('SPORTS');
insert into category (category_type) VALUES ('ENTERTAINMENT');
insert into category (category_type) VALUES ('MUSIC');
insert into category (category_type) VALUES ('DAILY');
insert into category (category_type) VALUES ('KNOWLEDGE');
insert into category (category_type) VALUES ('KIDS');
insert into category (category_type) VALUES ('PET');
insert into category (category_type) VALUES ('FOOD');


insert into user (nick_name, uid, PROFILE_URL) VALUES ('test', 'test', 'profileImg');
insert into user (nick_name, uid, PROFILE_URL) VALUES ('tttt', 'test2', 'testImg');

insert into you_tuber (CATEGORY_ID, CHANNEL_ID, DESCRIPTION, NAME, SUBSCRIBER_COUNT, THUMBNAIL, VIDEO_COUNT, BANNER_IMG_URL, COMMENT_COUNT, COUNTRY, LIKE_COUNT, NO_COUNT)
values (2L, 'testChannelId', 'Test입니다', '유튜버', 10L, 'Hello', 20L, 'bannerImg', 40L, 'KR', 0L, 0L);

insert into tag (MAIN_TAG, SUB_TAG) values ('COMMON', '선정적');
insert into tag (MAIN_TAG, SUB_TAG) values ('COMMON', '폭력적');
insert into tag (MAIN_TAG, SUB_TAG) values ('COMMON', '공포');
insert into tag (MAIN_TAG, SUB_TAG) values ('COMMON', '욕설');
insert into tag (MAIN_TAG, SUB_TAG) values ('COMMON', '자막제공');

insert into tag (MAIN_TAG, SUB_TAG) values ('GAME', 'PC게임');
insert into tag (MAIN_TAG, SUB_TAG) values ('GAME', '콘솔게임');

insert into tag (MAIN_TAG, SUB_TAG) values ('BEAUTY', '남자패션');
insert into tag (MAIN_TAG, SUB_TAG) values ('BEAUTY', '여성패션');
insert into tag (MAIN_TAG, SUB_TAG) values ('BEAUTY', '메이크업');
insert into tag (MAIN_TAG, SUB_TAG) values ('BEAUTY', '리뷰');