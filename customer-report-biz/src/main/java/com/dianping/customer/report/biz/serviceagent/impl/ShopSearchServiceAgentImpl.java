package com.dianping.customer.report.biz.serviceagent.impl;

import com.dianping.customer.report.biz.serviceagent.ShopSearchServiceAgent;

/**
 * Created by shenyoujun on 14-10-16.
 */
//@Component
public class ShopSearchServiceAgentImpl implements ShopSearchServiceAgent {

    public static final int CATEGORY_ID = 0;

    public static final int SORT_BY = 0;


//    @Autowired
//    private ShopSearchService shopSearchService;
//    @Override
//    public List<CrmShop> searchAllShops(String keyword, int userId, int pageIndex, int pageSize) {
//        try{
//            Tuple<Integer, List<CrmShop>> result_tuple = shopSearchService.searchAllShops(keyword, userId,CATEGORY_ID, SORT_BY,pageIndex, pageSize);
//            if(result_tuple!=null) return result_tuple.getY();
//
//            return new ArrayList<CrmShop>();
//        }catch (Exception e){
//            throw new ApplicationException( ApplicationExceptionEnum.OPERATION_FAILED,"获取分店信息失败");
//        }
//    }
}
