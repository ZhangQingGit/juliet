package com.ingooo.juliet.service.impl;

import com.ingooo.juliet.entity.Bid_Relation;
import com.ingooo.juliet.entity.Company;
import com.ingooo.juliet.entity.CompanyV;
import com.ingooo.juliet.mapper.CompanyMapper;
import com.ingooo.juliet.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper mapper;


    @Override
    public Integer companyReg(String cname, String synopsis, Integer uid, String cemail, String cphone, String involve, String homepage, String capital) {
        Integer i = mapper.findCidByCname(cname);
        if(i==null){
            return mapper.companyReg(cname, synopsis, uid, cemail, cphone, involve,homepage, capital);
        }
        return -1;
    }

    @Override
    public String getNameById(Integer uid) {
        Company company = mapper.findByUid(uid);
        if(company!=null) {
            return company.getCname();
        }
        return null;
    }

    @Override
    public CompanyV taskShow(String cname) {
        return mapper.getComByName(cname);
    }

    @Override
    public Company findById(Integer cid) {
        return mapper.findById(cid);
    }

    @Override
    public List<Bid_Relation> findIntentionPnameByCname(String cname) {
        return mapper.findIntentionPnameByCname(cname);
    }

    @Override
    public List<Bid_Relation> findIntentionTnameByPname(String pname) {
        return mapper.findIntentionTnameByPname(pname);
    }

    @Override
    public Integer updateCompanyInformationByUid(Company company) {
        return mapper.updateCompanyInformationByUid(company);
    }


}
