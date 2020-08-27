```
대규모 공사중 건들지마시오.
```


```
use areuareu;
select * from product;
select * from product_status;
select * from related_product;

update related_product set relatedProductId = 0 where id=51;


#이건 자식에 연결되어있는거
select *
from product pro inner join product_status pst inner join related_product rel
on pro.id = pst.productid and pro.id = rel.relatedProductId;


#이건 자식에 연결되어있는거
select *
from product pro inner join product_status pst inner join related_product rel
on pro.id = pst.productid and pro.id = rel.parentProductid and rel.relatedProductId=23;

select pro.title,pro.thumb,pro.price,pro.disc,pro.discounted,pro.content,pro.categoryId,pro.bgImg,pst.sale,pst.newly,pst.best,rel.parentProductId,rel.relatedProductId,ca.parentTypeId
from product pro inner join related_product rel inner join product_status pst inner join category ca
on pro.id= rel.parentProductId and pro.id = pst.productId and ca.parentTypeId = pro.categoryId;
```