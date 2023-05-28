with user_page as (
    select *
    from chat.users
    offset 0 limit 5),
     t as (
         select up.id           as user_id,
                up.login        as user_login,
                up.password     as user_pass,
                cr.id           as owner_chat_id,
                cr.name         as owner_chat_name,
                ucr.chatroom_id as socializes_chat_id
         from user_page up
                  left join chat.chatroom cr on up.id = cr.owner
                  left join chat.user_x_chatroom ucr on up.id = ucr.user_id
         order by up.id, cr.id, ucr.chatroom_id)
select t.*, cr.name as socializes_chat_name
from t
         left join chat.chatroom cr on t.socializes_chat_id = cr.id