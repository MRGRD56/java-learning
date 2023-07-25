import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class Program35 {
    public static void main(String[] args) {
        TaskAlertProject taskAlertProject = new TaskAlertProject(List.of("one", "two", "three"), -142523535L, "DEFAULT");
        System.out.println(taskAlertProject.toString());
    }

    private static class CustomToStringStyle extends ToStringStyle {
        public CustomToStringStyle() {
            this.setUseShortClassName(true);
            this.setUseIdentityHashCode(false);

            this.setFieldSeparator(", ");
        }
    }

    private static class TaskAlertProject {
        private List<String> jqlQueries;
        private Long alertTelegramChatId;
        private String messageTemplate;

        public TaskAlertProject(List<String> jqlQueries, Long alertTelegramChatId, String messageTemplate) {
            this.jqlQueries = jqlQueries;
            this.alertTelegramChatId = alertTelegramChatId;
            this.messageTemplate = messageTemplate;
        }

        public List<String> getJqlQueries() {
            return jqlQueries;
        }

        public void setJqlQueries(List<String> jqlQueries) {
            this.jqlQueries = jqlQueries;
        }

        public Long getAlertTelegramChatId() {
            return alertTelegramChatId;
        }

        public void setAlertTelegramChatId(Long alertTelegramChatId) {
            this.alertTelegramChatId = alertTelegramChatId;
        }

        public String getMessageTemplate() {
            return messageTemplate;
        }

        public void setMessageTemplate(String messageTemplate) {
            this.messageTemplate = messageTemplate;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, new CustomToStringStyle())
                    .append("jqlQueries", jqlQueries)
                    .append("alertTelegramChatId", alertTelegramChatId)
                    .append("messageTemplate", messageTemplate)
                    .toString();
        }
    }
}
