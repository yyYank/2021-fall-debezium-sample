create table albums
(
    id         bigint auto_increment primary key,
    name       varchar(255) not null,
    created_at timestamp    not null default current_timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tracks
(
    id         bigint auto_increment primary key,
    no         int,
    name       varchar(255) not null,
    review     int,
    created_at timestamp    not null default current_timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
