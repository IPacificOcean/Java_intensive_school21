with user_page as (
    select *
    from chat.users
    offset ? limit ?),
     t as (
         select up.id           as u_id,
                up.login        as u_login,
                up.password     as u_pass,
                cr.id           as owner_chat_id,
                cr.name         as owner_chat_name,
                uxc.chatroom_id as socializes_chat_id
         from user_page as up
                  left join chat.chatroom as cr on up.id = cr.owner
                  left join chat.user_x_chatroom as uxc on up.id = uxc.user_id
         order by up.id, cr.id, uxc.chatroom_id)
select t.*, cr.name as socializes_chat_name
from t
         left join chat.chatroom as cr on t.socializes_chat_id = cr.id