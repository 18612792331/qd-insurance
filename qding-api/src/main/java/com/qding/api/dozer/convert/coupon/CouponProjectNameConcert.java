package com.qding.api.dozer.convert.coupon;

import com.google.common.collect.Lists;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.util.QDStringUtil;
import org.dozer.CustomConverter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by qd on 2015/9/22.
 */
public class CouponProjectNameConcert implements CustomConverter {


    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        List<String> list = Lists.newArrayList();

        if (true) {
            return  list;
        }
        ProjectReadRemote projectReadService = ApplicationContextUtil.getBeansOfType(ProjectReadRemote.class);
        String projectIds = QDStringUtil.isNotNull(sourceFieldValue) ? String.valueOf(sourceFieldValue) : "";
        if (QDStringUtil.isNotNull(projectIds) && QDStringUtil.isNotEmpty(projectIds)) {
            String[] projectIdArray = projectIds.split(",");
            Set<String> projectSet = new HashSet(Arrays.asList(projectIdArray));
            List<Project>  projects = projectReadService.getByIds(projectSet);
            for (int i=0;i<projects.size();i++) {
               try {
                   list.add(projects.get(i).getName());
               } catch (Exception e) {
                   continue;
               }
            }
        }
        return list;
    }

}
