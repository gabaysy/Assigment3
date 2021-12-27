package bgu.spl.net.srv.BGS.msg;
import bgu.spl.net.srv.BGS.FilteredWords;

import java.util.Set;

public class PMMsg implements Message{
    final short optCode;
    final String username;
    final String content;
    final String sendingDateTime;


    public PMMsg(String username, String content, String sendingDateTime) {
        this.optCode = 6;
        this.username = username;
        this.content=filterContent(content);
        if(isDateTimeValid(sendingDateTime)) //Todo need to validate?
            this.sendingDateTime=sendingDateTime;
        else
            this.sendingDateTime="00-00-0000 00:00";
    }

    public short getOptCode() {
        return optCode;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }


    public String getSendingDateTime() {
        return sendingDateTime;
    }


    private boolean isDateTimeValid(String sendingDateTime){ //Todo
        return true;
    }

    private String filterContent(String content){
        String filterContent=content;
        Set<String> wordsToFilter=FilteredWords.wordsToFilter();
        for (String forbiddenWord: wordsToFilter) {
            if(content.contains(forbiddenWord)){
                filterContent=filterContent.replaceAll(forbiddenWord,"<filtered>");
            }
        }
        return filterContent;
    }
}

