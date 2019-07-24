package com.xuecheng.ucenter.dao;

        import com.xuecheng.framework.domain.ucenter.XcCompanyUser;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 98050
 * @Time: 2019-07-24 22:52
 * @Feature:
 */
public interface XcCompanyUserRepository extends JpaRepository<XcCompanyUser,String> {

    /**
     * 根据用户id查询关系表
     * @param userId
     * @return
     */
    XcCompanyUser findXcCompanyUserByUserId(String userId);
}
