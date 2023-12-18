create table category (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB;
create table ingredient (amount decimal(38,2), id bigint not null auto_increment, recipe_id bigint, unit_of_measure_id bigint, description varchar(255), primary key (id)) engine=InnoDB;
create table notes (id bigint not null auto_increment, recipe_id bigint, recipe_notes tinytext, primary key (id)) engine=InnoDB;
create table recipe (cook_time integer not null, prep_time integer not null, serving integer not null, id bigint not null auto_increment, notes_id bigint, description varchar(255), difficulty enum ('EASY','HARD','MODERATE'), source varchar(255), url varchar(255), directions tinytext, image tinyblob, primary key (id)) engine=InnoDB;
create table recipe_category (category_id bigint not null, recipe_id bigint not null, primary key (category_id, recipe_id)) engine=InnoDB;
create table unit_of_measure (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB;
alter table notes add constraint UK_gyihyf17jnihru8eoym1tmucb unique (recipe_id);
alter table recipe add constraint UK_61xwuqykaisawug77hnwnhg3i unique (notes_id);
alter table ingredient add constraint FKj0s4ywmqqqw4h5iommigh5yja foreign key (recipe_id) references recipe (id);
alter table ingredient add constraint FK15ttfoaomqy1bbpo251fuidxw foreign key (unit_of_measure_id) references unit_of_measure (id);
alter table notes add constraint FKdbfsiv21ocsbt63sd6fg0t3c8 foreign key (recipe_id) references recipe (id);
alter table recipe add constraint FK37al6kcbdasgfnut9xokktie9 foreign key (notes_id) references notes (id);
alter table recipe_category add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (category_id) references category (id);
alter table recipe_category add constraint FKcqlqnvfyarhieewfeayk3v25v foreign key (recipe_id) references recipe (id);
