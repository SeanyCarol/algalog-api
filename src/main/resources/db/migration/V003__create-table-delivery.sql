create table delivery (
  id bigint not null auto_increment,
  client_id bigint not null,
  rate decimal(10, 2) not null,
  status varchar(20) not null,
  order_date datetime not null,
  finalization_date datetime,

  recipient_name varchar(60) not null,
  recipient_public_area varchar(255) not null,
  recipient_number varchar(30) not null,
  recipient_address_complement varchar(60),
  recipient_neighborhood varchar(30) not null,

  primary key (id)
);

alter table delivery add constraint fk_delivery_client foreign key (client_id) references client (id);