CREATE TABLE t_user (
    id CHAR(10) PRIMARY KEY,
    user_password VARCHAR(20) NOT NULL,
    nickname VARCHAR(10) DEFAULT 'bobo',
    email VARCHAR(20) NOT NULL,
    phone CHAR(11) NOT NULL,
    gender CHAR(1) ,  -- 假设默认性别为男性
    introduction VARCHAR(200)
);

CREATE TABLE t_work (
    id INT PRIMARY KEY AUTO_INCREMENT,  -- 更改为 INT 类型并自增
    user_id CHAR(10) NOT NULL,
    work_type VARCHAR(10),
    tag VARCHAR(100),
    work_date DATE NOT NULL,
    content TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES t_user(id)
);

CREATE TABLE t_comment (
    id INT PRIMARY KEY AUTO_INCREMENT,  -- 更改为 INT 类型并自增
    work_id INT,
    source_user_id CHAR(10) NOT NULL,
    target_user_id CHAR(10),
    comment_date DATETIME NOT NULL,
    content VARCHAR(255) NOT NULL,
    FOREIGN KEY (work_id) REFERENCES t_work(id),
    FOREIGN KEY (source_user_id) REFERENCES t_user(id),
    FOREIGN KEY (target_user_id) REFERENCES t_user(id)
);

CREATE TABLE t_image (
    id INT PRIMARY KEY AUTO_INCREMENT,  -- 更改为 INT 类型并自增
    work_id INT NOT NULL,
    content BLOB NOT NULL,
    FOREIGN KEY (work_id) REFERENCES t_work(id)
);
