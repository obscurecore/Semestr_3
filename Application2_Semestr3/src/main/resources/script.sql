create schema public;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

create table if not exists category
(
	id integer not null
		constraint category_pk
			primary key,
	name varchar(60) not null,
	url varchar(60) not null,
	product_count integer default 0 not null
);

alter table category owner to postgres;

create unique index if not exists category_url_uindex
	on category (url);

create table if not exists producer
(
	id integer not null
		constraint producer_pk
			primary key,
	name varchar(60) not null,
	product_count integer default 0 not null
);

alter table producer owner to postgres;

create table if not exists product
(
	id integer not null
		constraint product_pk
			primary key,
	"name " varchar(255) not null,
	description text not null,
	image_link varchar(255) not null,
	price numeric(8,2),
	id_category integer not null
		constraint id_category
			references category
				on update cascade on delete restrict,
	id_producer integer not null
		constraint id_producer
			references producer
				on update cascade on delete restrict
);

alter table product owner to postgres;

create table if not exists account
(
	id integer not null
		constraint account_pk
			primary key,
	name varchar(60) not null,
	email varchar(100) not null
);

alter table account owner to postgres;

create unique index if not exists account_email_uindex
	on account (email);

create table if not exists "order"
(
	id bigint not null
		constraint order_pk
			primary key,
	id_account integer not null
		constraint id_account
			references account
				on update cascade on delete cascade,
	created timestamp default now() not null,
	id_product integer not null
		constraint id_product
			references product
				on update cascade on delete restrict
);

alter table "order" owner to postgres;


