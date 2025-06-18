CREATE TABLE mem(
	 num number primary key ,
	 name varchar(10) not null,
	 age number default 1,
	 loc varchar(50)
	);
    
    
CREATE SEQUENCE mem_num_seq
 START WITH 1
INCREMENT By 1
NOCACHE
NOCYCLE;

INSERT INTO mem(num,name,age,loc) VALUES(mem_num_seq.nextval, '홍길동',10,'서울');
COMMIT;
SELECT * FROM mem;