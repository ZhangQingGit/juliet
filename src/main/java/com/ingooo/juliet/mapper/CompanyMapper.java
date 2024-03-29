package com.ingooo.juliet.mapper;

import com.ingooo.juliet.entity.Bid_Relation;
import com.ingooo.juliet.entity.Company;
import com.ingooo.juliet.entity.CompanyV;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyMapper {
   /**
    * 根据uid 查看用户是否存在，一个uid对应一个公司
    * @param uid
    * @return
    */
    Company findByUid(Integer uid);

    /**
     * 公司注册
     * @param cname 公司名
     * @param synopsis 公司简介
     * @param uid 用户id
     * @param cemail 公司邮箱
     * @param cphone 公司联系方式
     * @param involve 涉及领域
     * @param homepage 公司主页
     * @param capital 注册资金
     * @return 受影响行数
     */
    Integer companyReg(@Param("cname") String cname
            , @Param("synopsis") String synopsis
            , @Param("uid") Integer uid
            , @Param("cemail") String cemail
            , @Param("cphone") String cphone, @Param("involve") String involve
            , @Param("homepage") String homepage, @Param("capital") String capital);

    /**
     * 根据公司名查出一条公司信息，用于任务列表的展示
     * @param cname
     * @return
     */
    CompanyV getComByName(String cname);

    /**
     * 根据cid查出公司的具体信息进行公司详情页的展示
     * @param cid
     * @return
     */
    Company findById(Integer cid);

    /**
     * 根据canme查询该用户有意向的项目名
     * @param cname
     * @return
     */
    List<Bid_Relation> findIntentionPnameByCname(String cname);

    /**
     * 根据panme查询该用户有意向的项目名下的团队名
     * @param pname
     * @return
     */
    List<Bid_Relation> findIntentionTnameByPname(String pname);

    /**
     * 修改个人/公司的个人信息
     * @param company
     * @return
     */
    Integer updateCompanyInformationByUid(Company company);

    /**
     * 根据公司名字查cid，看公司是否存在
     * @param cname
     * @return
     */
    Integer findCidByCname(String cname);
}
