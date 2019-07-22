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