delete from short_url;
delete from full_url;

insert into  full_url (id, url) values (1, 'http://google.com');

insert into short_url(id, url, full_url_id, enabled) values (1, 'http://s.com/gl1',1, true);
insert into short_url(id, url, full_url_id, enabled) values (2, 'http://s.com/gl11',1, true);

insert into  full_url (id, url) values (2, 'http://google.com2');
insert into  short_url(id, url, full_url_id, enabled) values (3, 'http://s.com/gl2',2, true);
insert into  short_url(id, url, full_url_id, enabled) values (4, 'http://s.com/gl22',2, true);
