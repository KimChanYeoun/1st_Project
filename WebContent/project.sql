DROP TABLE ADMIN;
DROP TABLE CUSTOMER;
DROP TABLE NOTICE;
DROP TABLE UPGRADE;
DROP TABLE BOARD;
DROP TABLE QnA;
DROP TABLE GALLERY;
DROP TABLE ERRORBOARD;
DROP TABLE ITEM;
DROP TABLE MYSTORE;

DROP SEQUENCE BOARD_SEQ;
DROP SEQUENCE QnA_SEQ;
DROP SEQUENCE GALLERY_SEQ;
DROP SEQUENCE NOTICE_SEQ;
DROP SEQUENCE UPDATE_SEQ;
DROP SEQUENCE ERRORBOARD_SEQ;
DROP SEQUENCE ITEM_SEQ;
DROP SEQUENCE MYSTORE_SEQ;

CREATE SEQUENCE BOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE SEQUENCE QnA_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE SEQUENCE GALLERY_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE SEQUENCE NOTICE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE SEQUENCE UPGRADE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE SEQUENCE ERRORBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE SEQUENCE ITEM_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE SEQUENCE MYSTORE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
    

CREATE TABLE ADMIN(
    aId VARCHAR2(30) PRIMARY KEY,
    aPw VARCHAR2(30) NOT NULL,
    aName VARCHAR2(30) NOT NULL,
    aPoint NUMBER(5) DEFAULT 0);


CREATE TABLE NOTICE(
    nNum NUMBER(5) PRIMARY KEY,
    aId VARCHAR2(30) REFERENCES ADMIN(aId),
    nTitle VARCHAR2(300) NOT NULL,
    nContent VARCHAR2(1000),
    nDate DATE DEFAULT SYSDATE);
    
    
CREATE TABLE UPGRADE(
    uNum NUMBER(5) PRIMARY KEY,
    aId VARCHAR2(30) REFERENCES ADMIN(aId),
    uTitle VARCHAR2(300) NOT NULL,
    uContent VARCHAR2(1000),
    uDate DATE DEFAULT SYSDATE);
    
    
CREATE TABLE CUSTOMER(
    cId VARCHAR2(30) PRIMARY KEY,
    cPw VARCHAR2(30) NOT NULL,
    cName VARCHAR2(30) NOT NULL,
    cPhoto VARCHAR2(100),
    cBirth DATE NOT NULL,
    cTel VARCHAR2(30) NOT NULL,
    cEmail VARCHAR2(30) NOT NULL,
    cGender VARCHAR2(10) NOT NULL,
    cAddr VARCHAR2(100) NOT NULL,
    cDate DATE DEFAULT SYSDATE,
    cPoint NUMBER(5) DEFAULT 0 NOT NULL);
    
    
CREATE TABLE BOARD(
    bNum NUMBER(5) PRIMARY KEY,
    cId VARCHAR2(30) REFERENCES CUSTOMER(cId),
    bTitle VARCHAR2(300) NOT NULL,
    bContent VARCHAR2(1000),
    bHit     NUMBER(5) DEFAULT 0,
    bGroup   NUMBER(5) NOT NULL,
    bStep    NUMBER(5) NOT NULL,
    bIndent  NUMBER(2) NOT NULL,
    bIp      VARCHAR2(20) NOT NULL,
    bDate    DATE DEFAULT SYSDATE);
    
    
CREATE TABLE GALLERY(
    gNum NUMBER(5) PRIMARY KEY,
    cId VARCHAR2(30) REFERENCES CUSTOMER(cId),
    gTitle VARCHAR2(300) NOT NULL,
    gContent VARCHAR2(1000),
    gfileName VARCHAR2(30),
    gHit     NUMBER(5) DEFAULT 0,
    gGroup   NUMBER(5) NOT NULL,
    gStep    NUMBER(5) NOT NULL,
    gIndent  NUMBER(2) NOT NULL,
    gIp      VARCHAR2(20) NOT NULL,
    gDate    DATE DEFAULT SYSDATE);
    
    
CREATE TABLE QnA(
    qNum NUMBER(5) PRIMARY KEY,
    cId VARCHAR2(30) REFERENCES CUSTOMER(cId),
    aId VARCHAR2(30) REFERENCES ADMIN(aId),
    qTitle VARCHAR2(300) NOT NULL,
    qContent VARCHAR2(1000),
    qHit     NUMBER(5) DEFAULT 0,
    qGroup   NUMBER(5) NOT NULL,
    qStep    NUMBER(5) NOT NULL,
    qIndent  NUMBER(2) NOT NULL,
    qIp      VARCHAR2(20) NOT NULL,
    qDate    DATE DEFAULT SYSDATE);
    
    
CREATE TABLE ERRORBOARD(
    eNum NUMBER(5) PRIMARY KEY,
    cId VARCHAR2(30) REFERENCES CUSTOMER(cId),
    aId VARCHAR2(30) REFERENCES ADMIN(aId),
    eTitle VARCHAR2(300) NOT NULL,
    eContent VARCHAR2(1000),
    efileName VARCHAR2(30),
    eGroup   NUMBER(5) NOT NULL,
    eStep    NUMBER(5) NOT NULL,
    eIndent  NUMBER(2) NOT NULL,
    eIp      VARCHAR2(20) NOT NULL,
    eDate    DATE DEFAULT SYSDATE);
    
    
CREATE TABLE ITEM(
   iNum NUMBER(5) PRIMARY KEY,
   iName VARCHAR2(30) NOT NULL,
   iPrice NUMBER(5) NOT NULL,
   iPictrue VARCHAR2(100));
    
    
CREATE TABLE MYSTORE(
    sItem NUMBER(5) PRIMARY KEY,
    iNum NUMBER(5)REFERENCES ITEM(iNum),
    cId VARCHAR2(30) REFERENCES CUSTOMER(cId));
    

----------------------------------------------------------------------
--                          admin table                             --
----------------------------------------------------------------------

-- 관리자 가입

INSERT INTO ADMIN (AID, APW, ANAME, APOINT)
  VALUES('admin', 'admin', 'admin', 0);

DELETE FROM ADMIN WHERE AID = 'admin';

select * from admin;
commit;

-- admin loginCheck
SELECT * FROM ADMIN WHERE AID='admin' AND APW='admin';

-- admin aid로 dto 가져오기
SELECT * FROM ADMIN WHERE AID='admin';

COMMIT;

select * from admin;



----------------------------------------------------------------------
--                          회원                                     --
----------------------------------------------------------------------
INSERT INTO CUSTOMER (CID, CPW, CNAME, CBIRTH, CTEL, CEMAIL, CGENDER, CADDR)
  VALUES('kcn1201', '1201', '김찬년', TO_DATE('19941201','YYYYMMDD'), '01075596366', 'kcn1201@naver.com', '남', '서울시 중랑구 망우동');

select * from CUSTOMER;
commit;    


-- 회원정보 삭제
DELETE FROM CUSTOMER WHERE CID = 'zzz';

-- 회원 정보 수정
UPDATE CUSTOMER SET CPW = '1201',
                    CTEL = '010-9954-6366',
                    CPHOTO = 'NULL',
                    CADDR = '서울시 중랑구 면목동',
                    CEMAIL = 'aazzzaa94@gmail.com'
            WHERE CID = 'kcn1201';
            
select * from CUSTOMER;
commit; 

SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
            (SELECT * FROM CUSTOMER ORDER BY CDATE) A) 
			 WHERE RN BETWEEN 1 AND 3;
    
----------------------------------------------------------------------
--                          BOARD table                             --
----------------------------------------------------------------------


-- 더미데이터(원글)
INSERT INTO BOARD (bNum, cId, bTitle, bContent, bGroup,  
        bStep, bIndent, bIp)
    VALUES (BOARD_SEQ.NEXTVAL, 'kcn1201','bTitle3','bContent3',
        BOARD_SEQ.CURRVAL, 0, 0, '192.168.10.151');
        
SELECT * FROM BOARD;
commit;

-- 더미데이터(위의 1번글에 대한 첫번째 답변글)
INSERT INTO BOARD (bNum, cId, bTitle, bContent, bGroup,  
        bStep, bIndent, bIp)
    VALUES (BOARD_SEQ.NEXTVAL, 'kcn1201','reply','content',
        5, 1, 1, '192.168.10.151');
        
select * from board order by bgroup desc, bstep;
commit;
-- 더미데이터 (위의 1번글에 대한 두번째 답변글)

INSERT INTO BOARD (bNum, cId, bTitle, bContent, bGroup,  
        bStep, bIndent, bIp)
    VALUES (BOARD_SEQ.NEXTVAL, 'kcn1201','reply','content',
        1, 2, 1, '192.168.10.151');



--    답변글 추가전 STEP a 수행
UPDATE BOARD SET bStep = bStep+1 
    WHERE bGroup = 1 AND bStep>0;
    
INSERT INTO BOARD (bNum, cId, bTitle, bContent, bGroup,  
        bStep, bIndent, bIp)
    VALUES (BOARD_SEQ.NEXTVAL, 'kcn1201','reply','content',
        1, 1, 1, '192.168.10.151');
        

select * from board order by bgroup desc, bstep;
COMMIT;


-- 글목록(startRow부터 endRow까지)
SELECT B.*, CNAME FROM BOARD B, CUSTOMER C WHERE B.CID=C.CID ORDER BY BGROUP DESC, BSTEP;

SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT B.*, CNAME FROM BOARD B, CUSTOMER C WHERE B.CID=C.CID ORDER BY BGROUP DESC, BSTEP) A)
    WHERE RN BETWEEN 2 AND 3;
    
SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT B.*, CNAME FROM BOARD B, CUSTOMER C WHERE B.CID=C.CID ORDER BY BGROUP DESC, BSTEP) A)
    WHERE cid = 'kcn1201' and RN BETWEEN 2 AND 3;
    

-- 글갯수
SELECT COUNT(*) FROM BOARD;

        
-- bHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE BOARD SET BHIT = BHIT +1 WHERE BNum=1;


-- bId로 글 dto보기
SELECT B.*, CNAME FROM BOARD B, CUSTOMER C WHERE C.CID=B.CID AND bNum=1;

-- 글 수정하기(BId, BTitle, BContent, BFILENAME,  BIp, BDATE)
UPDATE BOARD SET BTITLE = '제목',
                    BCONTENT = '본문',
                    BIP = '192.168.151.10',
                    BDATE = SYSDATE
            WHERE bNum = 1;
            
            
-- 글 삭제하기(bId로 삭제하기)
DELETE FROM BOARD WHERE BNUM=2;

select * from board order by bgroup desc, bstep;
COMMIT;

----------------------------------------------------------------------
--                          NOTICE table                             --
----------------------------------------------------------------------
-- 더미데이터
INSERT INTO NOTICE (nNum, AID, nTitle, nContent)
    VALUES (NOTICE_SEQ.NEXTVAL, 'admin','nTitle','nContent');
        
SELECT * FROM NOTICE;
commit;

        
select * from NOTICE order by nNum desc;
commit;



-- 글목록(startRow부터 endRow까지)
SELECT N.*, ANAME FROM NOTICE N, ADMIN A WHERE N.AID=A.AID ORDER BY nNum DESC;

SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT N.*, ANAME FROM NOTICE N, ADMIN A WHERE N.AID=A.AID ORDER BY nNum DESC) A)
    WHERE RN BETWEEN 1 AND 3;
    

-- 글갯수
SELECT COUNT(*) FROM NOTICE;


-- NId로 글 dto보기
SELECT N.*, ANAME FROM NOTICE N, ADMIN A WHERE A.AID=N.AID AND NNum=1;

-- 글 수정하기(NTitle, NContent)
UPDATE NOTICE SET NTITLE = '제목',
                  NCONTENT = '본문',
                  NDATE = SYSDATE
            WHERE NNum = 1;
            
            
-- 글 삭제하기(NId로 삭제하기)
DELETE FROM NOTICE WHERE NNUM=12;

select * from NOTICE order by nNum desc;
commit;


----------------------------------------------------------------------
--                          UPGRADE table                             --
----------------------------------------------------------------------
-- 더미데이터
INSERT INTO UPGRADE (UNum, AID, UTitle, UContent)
    VALUES (UPGRADE_SEQ.NEXTVAL, 'admin','UTitle1','UContent1');
        
SELECT * FROM UPGRADE;
commit;

        
select * from UPGRADE order by UNum desc;
commit;



-- 글목록(startRow부터 endRow까지)
SELECT U.*, ANAME FROM UPGRADE U, ADMIN A WHERE U.AID=A.AID ORDER BY UNum DESC;

SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT U.*, ANAME FROM UPGRADE U, ADMIN A WHERE U.AID=A.AID ORDER BY UNum DESC) A)
    WHERE RN BETWEEN 1 AND 3;
    

-- 글갯수
SELECT COUNT(*) FROM UPGRADE;


-- UId로 글 dto보기
SELECT U.*, ANAME FROM UPGRADE U, ADMIN A WHERE U.AID=A.AID AND UNum=5;

-- 글 수정하기(UId, UTitle, UContent, UFILENAME,  UIp, UDATE)
UPDATE UPGRADE SET UTITLE = '제목',
                  UCONTENT = '본문',
                  UDATE = SYSDATE
            WHERE UNum = 5;
            
            
-- 글 삭제하기(UId로 삭제하기)
DELETE FROM UPGRADE WHERE UNUM=6;

select * from UPGRADE order by UNum desc;
commit;


----------------------------------------------------------------------
--                          QnA table                             --
----------------------------------------------------------------------


-- 더미데이터(원글)
INSERT INTO QnA (QNum, cId, QTitle, QContent, QGroup,  
        QStep, QIndent, QIp)
    VALUES (QnA_SEQ.NEXTVAL, 'kcn1201','bTitle3','bContent3',
        QnA_SEQ.CURRVAL, 0, 0, '192.168.10.151');
        
SELECT * FROM QnA;
commit;

-- 더미데이터(위의 1번글에 대한 관리자 답변글)
INSERT INTO QnA (QNum, AId, QTitle, QContent, QGroup,  
        QStep, QIndent, QIp)
    VALUES (BOARD_SEQ.NEXTVAL, 'admin','reply','content',
        1, 1, 1, '192.168.10.151');
        
select * from QnA order by qgroup desc, qstep;
commit;


--    답변글 추가전 STEP a 수행
UPDATE QnA SET QStep = QStep+1 
    WHERE QGroup = 1 AND QStep>0;
    
INSERT INTO QnA (QNum, AId, QTitle, QContent, QGroup,  
        QStep, QIndent, QIp)
    VALUES (BOARD_SEQ.NEXTVAL, 'admin','reply','content',
        7, 1, 1, '192.168.10.151');
        
SELECT Q.*, CNAME FROM QNA Q, CUSTOMER C WHERE C.CID=Q.CID;

SELECT Q.*, ANAME FROM QNA Q, ADMIN A WHERE A.AID=Q.AID and qNum=123;

SELECT Q.*, ANAME FROM QNA Q, ADMIN A, CUSTOMER C WHERE A.AID=Q.AID or c.cid=q.cid and qNum=123;

select * from QnA order by Qgroup desc, Qstep;
COMMIT;


-- 글목록(startRow부터 endRow까지)
SELECT * FROM QnA ORDER BY Qgroup DESC, Qstep;

SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (select * from QnA order by Qgroup desc, Qstep) A)
    WHERE RN BETWEEN 1 AND 10;
    
    

-- 글갯수
SELECT COUNT(*) FROM QnA;

        
-- QHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE QnA SET QHIT = QHIT +1 WHERE QNum=1;


-- QId로 글 dto보기
SELECT * FROM QnA WHERE QNum=1;

-- 글 수정하기(QId, QTitle, QContent, QDATE)
UPDATE QnA SET QTITLE = '제목',
               QCONTENT = '본문',
               QIP = '192.168.151.10',
               QDATE = SYSDATE
            WHERE QNum = 1;
            
            
-- 글 삭제하기(QNUM으로 삭제하기)
DELETE FROM QnA WHERE QNUM=19;

select * from QnA order by Qgroup desc, Qstep;
COMMIT;


----------------------------------------------------------------------
--                          GALLERY table                           --
----------------------------------------------------------------------


-- 더미데이터(원글)
INSERT INTO GALLERY (GNUM, CID, GTITLE, GCONTENT, GFILENAME,  
        GGROUP, GSTEP, GINDENT, GIP)
    VALUES (GALLERY_SEQ.NEXTVAL, 'kcn1201','title','content',null, 
        GALLERY_SEQ.CURRVAL, 0, 0, '192.168.10.151');
        
        
SELECT * FROM GALLERY;
SELECT COUNT(*) FROM GALLERY WHERE CID = 'kcn1201';

-- 더미데이터(위의 1번글에 대한 첫번째 답변글)
INSERT INTO GALLERY (GNUM, CID, GTITLE, GCONTENT, GFILENAME,  
        GGROUP, GSTEP, GINDENT, GIP)
    VALUES (GALLERY_SEQ.NEXTVAL, 'kcn1201','title','content',null, 
        1, 1, 1, '192.168.10.151');
        
        
select * from GALLERY order by ggroup desc, gstep;

-- 더미데이터 (위의 1번글에 대한 두번째 답변글)
--    답변글 추가전 STEP a 수행
UPDATE GALLERY SET GSTEP = GSTEP+1 
    WHERE GGROUP = 1 AND GSTEP>0;
    
INSERT INTO GALLERY (GNum, CID, GTITLE, GCONTENT, GFILENAME,
        GGROUP, GSTEP, GINDENT, GIP)
    VALUES (GALLERY_SEQ.NEXTVAL, 'kcn1201','reply','content', null,
        1, 1, 1, '192.168.10.151');
COMMIT;

-- 글목록(startRow부터 endRow까지)
SELECT G.*, CNAME FROM GALLERY G, CUSTOMER C WHERE G.CID=C.CID ORDER BY GGROUP DESC, GSTEP;
SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT G.*, CNAME FROM GALLERY G, CUSTOMER C WHERE G.CID=C.CID ORDER BY GGROUP DESC, GSTEP) A)
    WHERE RN BETWEEN 2 AND 3;
    
SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT G.*, CNAME FROM GALLERY G, CUSTOMER C WHERE G.CID=C.CID  AND GINDENT = 0 ORDER BY GGROUP DESC, GSTEP) A)
    WHERE RN BETWEEN 1 AND 5;
    
-- 글갯수
SELECT COUNT(*) FROM GALLERY;

-- GHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE GALLERY SET GHIT = GHIT +1 WHERE GNUM=1;

-- GNUM로 글 dto보기
SELECT G.*, CNAME FROM GALLERY G, CUSTOMER C WHERE G.CID=C.CID AND GNUM=1;

-- 글 수정하기(GId, GTitle, GContent, GFILENAME,  GIp, GDATE)
UPDATE GALLERY SET GTITLE = '제목',
                    GCONTENT = '본문',
                    GFILENAME = NULL,
                    GIP = '192.168.151.10',
                    GDATE = SYSDATE
            WHERE GNUM = 1;
            
            
-- 글 삭제하기(CId로 삭제하기)
DELETE FROM GALLERY WHERE GNUM=2;

--    답변글 추가전 STEP a 수행
UPDATE GALLERY SET GSTEP = GSTEP+1 
    WHERE GGROUP = 1 AND GSTEP>0;
-- 답변글 쓰기
INSERT INTO GALLERY (GNUM, CID, GTITLE, GCONTENT, GFILENAME,
        GGROUP, GSTEP, GINDENT, GIP)
    VALUES (GALLERY_SEQ.NEXTVAL, 'kcn1201','reply','content', null,
        1, 1, 1, '192.168.10.151');

select * from GALLERY order by ggroup desc, gstep;
COMMIT;

----------------------------------------------------------------------
--                          ERRORBOARD table                        --
----------------------------------------------------------------------

-- 더미데이터(원글)
INSERT INTO ERRORBOARD (ENUM, CID, ETITLE, ECONTENT, EFILENAME,  
        EGROUP, ESTEP, EINDENT, EIP)
    VALUES (ERRORBOARD_SEQ.NEXTVAL, 'kcn1201','title','content',null, 
        ERRORBOARD_SEQ.CURRVAL, 0, 0, '192.168.10.151');
        
        
SELECT * FROM ERRORBOARD;


-- 더미데이터(위의 1번글에 대한 첫번째 답변글)
INSERT INTO ERRORBOARD (ENUM, AID, ETITLE, ECONTENT, EFILENAME,  
        EGROUP, ESTEP, EINDENT, EIP)
    VALUES (ERRORBOARD_SEQ.NEXTVAL, 'admin','title','content',null, 
        1, 1, 1, '192.168.10.151');
        
        
select * from ERRORBOARD order by egroup desc, estep;

-- 더미데이터 (위의 1번글에 대한 두번째 답변글)
--    답변글 추가전 STEP a 수행
UPDATE ERRORBOARD SET ESTEP = ESTEP+1 
    WHERE EGROUP = 1 AND ESTEP>0;
    
INSERT INTO ERRORBOARD (ENUM, AID, ETITLE, ECONTENT, EFILENAME,  
        EGROUP, ESTEP, EINDENT, EIP)
    VALUES (ERRORBOARD_SEQ.NEXTVAL, 'admin','title','content',null, 
        1, 1, 1, '192.168.10.151');
        
select * from ERRORBOARD order by egroup desc, estep;
COMMIT;

-- 글목록(startRow부터 endRow까지)
SELECT * FROM ERRORBOARD ORDER BY Egroup DESC, Estep;

SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (select * from ERRORBOARD order by Egroup desc, Estep) A)
    WHERE RN BETWEEN 2 AND 3;
    
-- 글갯수
SELECT COUNT(*) FROM ERRORBOARD;


-- ENUM로 글 dto보기
SELECT * FROM ERRORBOARD WHERE ENUM=1;

-- 글 수정하기(EId, ETitle, EContent, EFILENAME,  EIp, EDATE)
UPDATE ERRORBOARD SET ETITLE = '제목',
                    ECONTENT = '본문',
                    EFILENAME = NULL,
                    EIP = '192.168.151.10',
                    EDATE = SYSDATE
            WHERE ENUM = 1;
            
            
-- 글 삭제하기(Id로 삭제하기)
DELETE FROM ERRORBOARD WHERE ENUM=2;

--    답변글 추가전 STEP a 수행
UPDATE ERRORBOARD SET ESTEP = ESTEP+1 
    WHERE EGROUP = 1 AND ESTEP>0;
    
-- 답변글 쓰기
INSERT INTO ERRORBOARD (ENUM, AID, ETITLE, ECONTENT, EFILENAME,
        EGROUP, ESTEP, EINDENT, EIP)
    VALUES (ERRORBOARD_SEQ.NEXTVAL, 'admin','reply','content', null,
        1, 1, 1, '192.168.10.151');

select * from ERRORBOARD order by egroup desc, estep;
COMMIT;


----------------------------------------------------------------------
--                          ITEM table                              --
----------------------------------------------------------------------

--아이템 등록
INSERT INTO ITEM (INUM, INAME, IPRICE, IPICTRUE)
    VALUES (ITEM_SEQ.NEXTVAL,'ITEM',1000, null);
    
SELECT * FROM ITEM;

DELETE FROM ITEM WHERE INUM = 1;

COMMIT;

-- 아이템목록(startRow부터 endRow까지)
SELECT * FROM ITEM ORDER BY INUM DESC;

SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT * FROM ITEM ORDER BY INUM DESC) A)
    WHERE RN BETWEEN 2 AND 3;

--가격변경
UPDATE ITEM SET IPRICE = 500 WHERE INUM = 2;

--상세보기
SELECT * FROM ITEM WHERE INUM = 2;

COMMIT;

----------------------------------------------------------------------
--                          MYSTORE table                           --
----------------------------------------------------------------------

--개인정보보기
SELECT * FROM CUSTOMER WHERE CID = 'kcn1201';

SELECT COUNT(*) FROM BOARD WHERE CID = 'kcn1201';
SELECT COUNT(*) FROM GALLERY WHERE CID = 'kcn1201';
SELECT COUNT(*) FROM QnA WHERE CID = 'kcn1201';
SELECT COUNT(*) FROM ERRORBOARD WHERE CID = 'kcn1201';

--내가 쓴글 보기(BORDER게시판)
SELECT * FROM BOARD WHERE CID = 'kcn1201' ORDER BY BNUM DESC;

SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT * FROM BOARD WHERE CID = 'kcn1201' ORDER BY BNUM DESC) A)
    WHERE RN BETWEEN 2 AND 3;
    
--내가 쓴글 보기(GALLERY게시판)
SELECT * FROM GALLERY WHERE CID = 'kcn1201' ORDER BY GNUM DESC;

SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT * FROM GALLERY WHERE CID = 'kcn1201' ORDER BY GNUM DESC) A)
    WHERE RN BETWEEN 2 AND 3;
    
--내가 쓴글 보기(QnA게시판)
SELECT * FROM QnA WHERE CID = 'kcn1201' ORDER BY qNUM DESC;

SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT * FROM QnA WHERE CID = 'kcn1201' ORDER BY QNUM DESC) A)
    WHERE RN BETWEEN 1 AND 3;

--내가 쓴글 보기(ERRORBOARD게시판)
SELECT * FROM ERRORBOARD WHERE CID = 'kcn1201' ORDER BY ENUM DESC;

SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT * FROM ERRORBOARD WHERE CID = 'kcn1201' ORDER BY ENUM DESC) A)
    WHERE RN BETWEEN 1 AND 3;

--내 아이템 보기

INSERT INTO MYSTORE (SITEM, INUM, CID)
  VALUES(MYSTORE_SEQ.NEXTVAL, 2, 'kcn1201');

SELECT M.INUM, INAME, iPictrue FROM ITEM I, MYSTORE M
    WHERE M.CID = 'kcn1201' AND I.INUM=M.INUM ORDER BY INUM DESC;
    
SELECT * FROM MYSTORE;

SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT M.INUM, INAME, iPictrue FROM ITEM I, MYSTORE M
    WHERE M.CID = 'kcn1201' AND I.INUM=M.INUM ORDER BY INUM DESC) A)
    WHERE RN BETWEEN 1 AND 3;
    
commit;

SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT * FROM mystore WHERE CID = 'kcn1201' ORDER BY iNUM DESC) A)
    WHERE RN BETWEEN 1 AND 3;
