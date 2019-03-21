delete from short_url;
delete from full_url;

insert into  full_url (id, url) values (1, 'http://google.com');

insert into short_url(id, url, full_url_id) values (1, 'http://s.com/gl',1);
