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
insert into tag (MAIN_TAG, SUB_TAG) values ('COMMON', '자막 제공');

insert into tag (MAIN_TAG, SUB_TAG) values ('GAME', 'PC 게임');
insert into tag (MAIN_TAG, SUB_TAG) values ('GAME', '콘솔 게임');

insert into tag (MAIN_TAG, SUB_TAG) values ('BEAUTY', '남자 패션');
insert into tag (MAIN_TAG, SUB_TAG) values ('BEAUTY', '여성 패션');
insert into tag (MAIN_TAG, SUB_TAG) values ('BEAUTY', '메이크업');
insert into tag (MAIN_TAG, SUB_TAG) values ('BEAUTY', '뷰티 리뷰');

insert into tag (MAIN_TAG, SUB_TAG) values ('SPORTS', '경기');
insert into tag (MAIN_TAG, SUB_TAG) values ('SPORTS', '스포츠 리뷰');
insert into tag (MAIN_TAG, SUB_TAG) values ('SPORTS', '스포츠 강의');

insert into tag (MAIN_TAG, SUB_TAG) values ('ENTERTAINMENT', '웹드라마');
insert into tag (MAIN_TAG, SUB_TAG) values ('ENTERTAINMENT', '영화');
insert into tag (MAIN_TAG, SUB_TAG) values ('ENTERTAINMENT', '애니메이션');
insert into tag (MAIN_TAG, SUB_TAG) values ('ENTERTAINMENT', '크리에이티');

insert into tag (MAIN_TAG, SUB_TAG) values ('MUSIC', '춤');
insert into tag (MAIN_TAG, SUB_TAG) values ('MUSIC', '뮤직 강의');
insert into tag (MAIN_TAG, SUB_TAG) values ('MUSIC', '뮤비');
insert into tag (MAIN_TAG, SUB_TAG) values ('MUSIC', '플레이리스트');
insert into tag (MAIN_TAG, SUB_TAG) values ('MUSIC', '노래');
insert into tag (MAIN_TAG, SUB_TAG) values ('MUSIC', '악기');
insert into tag (MAIN_TAG, SUB_TAG) values ('MUSIC', '작곡');

insert into tag (MAIN_TAG, SUB_TAG) values ('DAILY', '브이로그');
insert into tag (MAIN_TAG, SUB_TAG) values ('DAILY', '여행');
insert into tag (MAIN_TAG, SUB_TAG) values ('DAILY', '육아');
insert into tag (MAIN_TAG, SUB_TAG) values ('DAILY', '토크');
insert into tag (MAIN_TAG, SUB_TAG) values ('DAILY', '코믹');
insert into tag (MAIN_TAG, SUB_TAG) values ('DAILY', '커플');
insert into tag (MAIN_TAG, SUB_TAG) values ('DAILY', 'ASMR');

insert into tag (MAIN_TAG, SUB_TAG) values ('KNOWLEDGE', '지식 리뷰');
insert into tag (MAIN_TAG, SUB_TAG) values ('KNOWLEDGE', '역사');
insert into tag (MAIN_TAG, SUB_TAG) values ('KNOWLEDGE', '강연');
insert into tag (MAIN_TAG, SUB_TAG) values ('KNOWLEDGE', '정치');
insert into tag (MAIN_TAG, SUB_TAG) values ('KNOWLEDGE', '시사');
insert into tag (MAIN_TAG, SUB_TAG) values ('KNOWLEDGE', '외국어');

insert into tag (MAIN_TAG, SUB_TAG) values ('KIDS', '동요');
insert into tag (MAIN_TAG, SUB_TAG) values ('KIDS', '만화');
insert into tag (MAIN_TAG, SUB_TAG) values ('KIDS', '장난감리뷰');
insert into tag (MAIN_TAG, SUB_TAG) values ('KIDS', '교육');
insert into tag (MAIN_TAG, SUB_TAG) values ('KIDS', '동화');

insert into tag (MAIN_TAG, SUB_TAG) values ('PET', '강아지');
insert into tag (MAIN_TAG, SUB_TAG) values ('PET', '고양이');
insert into tag (MAIN_TAG, SUB_TAG) values ('PET', '기타');

insert into tag (MAIN_TAG, SUB_TAG) values ('FOOD', '먹방');
insert into tag (MAIN_TAG, SUB_TAG) values ('FOOD', '레시피');


