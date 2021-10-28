package top.chaser.admin.api.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.chaser.admin.api.entity.UmsFuncResourceRelation;
import top.chaser.admin.api.service.UmsFuncResourceRelationService;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;

/**
 * 后台功能点和资源关系表(UmsFuncResourceRelation)表服务实现类
 *
 * @author yzb
 * @since 2021-10-27 09:41:28
 */
@Slf4j
@Service("umsFuncResourceRelationService")
public class UmsFuncResourceRelationServiceImpl extends TkServiceImpl<UmsFuncResourceRelation> implements UmsFuncResourceRelationService {

}
