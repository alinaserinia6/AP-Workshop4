import java.text.DateFormat;
import java.util.*;
import java.text.SimpleDateFormat;

public class Voting {
    private int type;
    private String question;
    private HashMap<String, HashSet<Vote>> choice;
    private boolean isAnonymous;
    private ArrayList<Person> voters;

    public Voting(int type, String question, boolean isAnonymous) {
        this.type = type;
        this.question = question;
        this.isAnonymous = isAnonymous;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getChoices() {
        ArrayList<String> res = new ArrayList<String>();

        for (Map.Entry<String, HashSet<Vote>> i : choice.entrySet())
            res.add(i.getKey());
        return res;
    }

    public void createChoices(String choice) {
        HashSet<Vote> now = new HashSet<Vote>();
        this.choice.put(choice, now);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voting voting)) return false;
        return type == voting.type && isAnonymous == voting.isAnonymous && getQuestion().equals(voting.getQuestion()) && choice.equals(voting.choice) && voters.equals(voting.voters);
    }

    public void vote(Person voter, ArrayList<String> voter_choices) {
        voters.add(voter);
        for (String i : voter_choices) {
            for  (Map.Entry<String, HashSet<Vote>> j : choice.entrySet()) {
                if (i.equals(j.getKey())) {
                    HashSet<Vote> now = j.getValue();
                    String pattern = "MM/dd/yyyy HH:mm:ss";
                    DateFormat df = new SimpleDateFormat(pattern);
                    Date today = Calendar.getInstance().getTime();
                    String todayAsString = df.format(today);
                    Vote man = new Vote(voter, todayAsString);
                    now.add(man);
                    j.setValue(now);
                    break;
                }
            }
        }
    }

}