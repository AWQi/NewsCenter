package com.example.dell.newscenter.myview.InfoActivity.history;

import android.content.Context;

import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.utils.JsonUtil;
import com.example.dell.newscenter.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class HistoryUtil {
    static public HistoryProject historyProject;
    static public String HISTORY_SHARED_IDENTIFICATION = "HISTORY_SHARED_IDENTIFICATION";

    static public void putHistory(Context context, Project project) {
        Date now = new Date();
        historyProject = new HistoryProject(project, now);
        String key = String.valueOf(project.getId());
        String value = JsonUtil.ObjToStr(historyProject);
        SharedPreferencesUtil.putStringSharedDate(context, HISTORY_SHARED_IDENTIFICATION, key, value);
    }

    static public List<Project> getHistory(Context context) {
        Map map = (Map) SharedPreferencesUtil.getAllSharedDate(context, HISTORY_SHARED_IDENTIFICATION);
        List<Project> projectList = new ArrayList<>();
        List<HistoryProject> historyProjectList = new ArrayList<>();
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
//        Integer key = (Integer)entry.getKey();
            String str = (String) entry.getValue();
            HistoryProject historyProject = JsonUtil.StrToObj(str, HistoryProject.class);
            historyProjectList.add(historyProject);
        }
        historyProjectList.sort(new Comparator<HistoryProject>() {
            @Override
            public int compare(HistoryProject o1, HistoryProject o2) {
                // 按时间从后往前排序
                if (o1.time.before(o2.time))
                    return -1;
                else
                    return 1;
            }
        });
        for (HistoryProject h : historyProjectList) {
            projectList.add(h.project);
        }
        return projectList;
    }

    static class HistoryProject {
        public Project project;
        public Date time;

        public HistoryProject(Project project, Date time) {
            this.project = project;
            this.time = time;
        }
    }
}
