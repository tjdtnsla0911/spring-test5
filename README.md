```
대규모 공사중 건들지마시오.
1차작업 20.08.27 21:00 끝

2차작업(예상) 20.08.27 22:15~01:00 (이때 작업끝날확률 23%)

3차작업(예상) 20.08.28 09:00~16:30 (이때 작업끝날확률 60%)

4차작업(예상) 20.08.28 16:45~21:00 (이떄 작업끝날확률 99%)

5차작업(예상) 20.08.28 21:00~~ 한강으로 (이때 까지못할확률 1%)무조
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
