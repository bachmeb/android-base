package us.proximal.spellwithme.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import us.proximal.spellwithme.R;
import us.proximal.spellwithme.controller.def.IPeopleService;
import us.proximal.spellwithme.controller.svc.PeopleService;
import us.proximal.spellwithme.model.dao.AnswersDAO;
import us.proximal.spellwithme.model.dao.PeopleDAO;
import us.proximal.spellwithme.model.dao.QuestionsDAO;
import us.proximal.spellwithme.model.dao.WordsDAO;
import us.proximal.spellwithme.model.def.IAnswersDAO;
import us.proximal.spellwithme.model.def.IPeopleDAO;
import us.proximal.spellwithme.model.def.IQuestionsDAO;
import us.proximal.spellwithme.model.def.IWordsDAO;
import us.proximal.spellwithme.model.dto.AnswerDTO;
import us.proximal.spellwithme.model.dto.PersonDTO;
import us.proximal.spellwithme.model.dto.QuestionDTO;
import us.proximal.spellwithme.model.dto.WordDTO;
import us.proximal.spellwithme.utility.DbUtil;

import static us.proximal.spellwithme.utility.ToastUtil.makeToast;

public class Prime extends BaseActivity {


    IWordsDAO daoWords;
    IQuestionsDAO daoQuestions;
    IPeopleDAO daoPeople;
    ArrayList<WordDTO> words;

    Button btnDatabaseExists;
    Button btnDatabaseCreate;
    Button btnDatabaseDelete;

    Button btnPrimeWords;
    Button btnPrimeQuestions;
    Button btnPrimeMe;

    Button btnToastWord;
    Button btnToastQuestion;
    Button btnToastMe;

    Button btnToastWords;
    Button btnToastQuestions;
    Button btnToastAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);

        //instantiate the buttons in the onCreate method
        btnDatabaseExists = (Button) findViewById(R.id.buttonPrimeDatabaseExists);
        btnDatabaseCreate = (Button) findViewById(R.id.buttonPrimeDatabaseCreate);
        btnDatabaseDelete = (Button) findViewById(R.id.buttonPrimeDatabaseDelete);

        btnPrimeWords = (Button) findViewById(R.id.buttonPrimeWords);
        btnPrimeQuestions = (Button) findViewById(R.id.buttonPrimeQuestions);
        btnPrimeMe = (Button) findViewById(R.id.buttonPrimeMe);

        btnToastWord = (Button) findViewById(R.id.buttonPrimeToastWord);
        btnToastQuestion = (Button) findViewById(R.id.buttonPrimeToastQuestion);
        btnToastMe = (Button) findViewById(R.id.buttonPrimeToastMe);

        btnToastWords = (Button) findViewById(R.id.buttonPrimeToastWords);
        btnToastQuestions = (Button) findViewById(R.id.buttonPrimeToastQuestions);
        btnToastAnswers = (Button) findViewById(R.id.buttonPrimeToastAnswers);


        //attach onClick listener to the button object
        btnDatabaseExists.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View currentView) {
                                                     databaseExists();
                                                 }
                                             }
        );

        //attach onClick listener to the button object
        btnDatabaseCreate.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View currentView) {
                                                     databaseCreate();
                                                 }
                                             }
        );

        //attach onClick listener to the button object
        btnDatabaseDelete.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View currentView) {
                                                     databaseDelete();
                                                 }
                                             }
        );

        //attach onClick listener to the button object
        btnPrimeWords.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View currentView) {
                                                 primeWords();
                                             }
                                         }
        );

        //attach onClick listener to the button object
        btnPrimeQuestions.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View currentView) {
                                                     primeQuestions();
                                                 }
                                             }
        );

        //attach onClick listener to the button object
        btnPrimeMe.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View currentView) {
                                              primeMe();
                                          }
                                      }
        );

        //attach onClick listener to the button object
        btnToastWord.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View currentView) {
                                                toastWord();
                                            }
                                        }
        );

        //attach onClick listener to the button object
        btnToastQuestion.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View currentView) {
                                                    toastQuestion();
                                                }
                                            }
        );

        //attach onClick listener to the button object
        btnToastMe.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View currentView) {
                                                    toastMe();
                                                }
                                            }
        );


        //attach onClick listener to the button object
        btnToastWords.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View currentView) {
                                                 toastWords();
                                             }
                                         }
        );

        //attach onClick listener to the button object
        btnToastQuestions.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View currentView) {
                                                     toastQuestions();
                                                 }
                                             }
        );

        //attach onClick listener to the button object
        btnToastAnswers.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View currentView) {
                                                   toastAnswers();
                                               }
                                           }
        );

    }

    private void databaseDelete() {

        //Create a prime object
        DbUtil prime = new DbUtil();

        boolean result = prime.delete(this);

        if (result) {
            makeToast(this, "The database has been deleted.");
        }
    }

    private void databaseExists() {

        //Create a prime object
        DbUtil prime = new DbUtil();

        // check if the database exists
        boolean exists = prime.exists(this);

        //debug
        //exists = true;

        // delete it if it exists
        if (exists) {
            makeToast(this, "The database does exist.");
        } else {
            makeToast(this, "The database does not exist.");
        }
    }

    public void databaseCreate() {

        //Create a prime object
        DbUtil prime = new DbUtil();

        // check if the database exists
        boolean exists = prime.exists(this);

        //debug
        //exists = true;

        // delete it if it exists
        if (exists) {
            prime.delete(this);
        }

        // create the new database
        prime.create(this);

    }

    public void toastWord() {

        //Make adapter
        IWordsDAO dao = new WordsDAO(this);

        //Make a DTO
        WordDTO word = new WordDTO();

        //Make an array list for the words
        ArrayList<WordDTO> list = null;

        //try to populate the list of words
        try {

            //Call the list() method to make an ArrayList of words
            list = dao.list();

            //Count the size() of the ArrayList
            int count = list.size();

            //Make a random number between 1 and the size() of the ArrayList
            int rand = (int) (count * Math.random());

            try {
                //word.setWord(dao.read("the").getWord());
                //Read a random word
                //word = dao.read(rand);

                word = dao.read(rand);

                makeToast(this, "The word is: " + word.getWord());

                // Toast a count of all records
                toastWords();

            } catch (Exception e) {

                makeToast(this, "Failed on wa.read(rand)");

                // Toast a count of all records
                toastWords();

                // Toast the exception
                makeToast(this, e.toString());

                e.printStackTrace();
            }

        } catch (Exception e) {

            //
            makeToast(this, "Failed on wa.list()");

            // Toast a count of all records
            toastWords();

            //
            e.printStackTrace();
        }

    }

    public void toastQuestion() {

        //Make a DAO
        IQuestionsDAO dao = new QuestionsDAO(this);
        //Make a DTO
        QuestionDTO dto = new QuestionDTO();
        //
        ArrayList<QuestionDTO> list = null;

        try {
            //Call the list() method to make an ArrayList of words
            list = dao.list();
            //Count the size() of the ArrayList
            int count = list.size();

            //Make a random number between 1 and the size() of the ArrayList
            int rand = (int) (count * Math.random());

            try {
                // Read a random record
                dto.setText(dao.read(rand).getText());

                // Toast it
                makeToast(this, "The question is: " + dto.getText());

                // Toast a count of all records
                //toastQuestions();

            } catch (Exception e) {

                //
                makeToast(this, "Failed on dao.read(rand)");

                //
                toastQuestions();

                // Toast the exception
                makeToast(this, e.toString());

                e.printStackTrace();
            }

        } catch (Exception e) {
            //
            makeToast(this, "Failed on dao.list()");
            //
            toastQuestions();
            //
            e.printStackTrace();
        }
    }


    public void toastMe() {

        IPeopleService svcPeople;
        svcPeople = new PeopleService(this);

        PersonDTO me = new PersonDTO();

        try {
            me = svcPeople.getMyPersonObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        makeToast(this, me.getFirstName() + " " + me.getLastName() + " " + me.getBirthDate() );

    }

    public void primeWords() {

        //
        makeToast(this, "Here we go...");
        //
        String[] dolch;
        //
        dolch = makeDolch();
        //
        for (int i = 0; i < dolch.length; i++) {
            WordDTO word = new WordDTO();
            word.setWord(dolch[i]);
            word.setDolch(true);
            word.setLength(dolch[i].length());
            try {
                //
                WordsDAO dao = new WordsDAO(this);
                //
                dao.create(word);

            } catch (Exception e) {
                makeToast(this, "Failed on wa.create");
                e.printStackTrace();
            }

        }
        //
        makeToast(this, "Call to fill database with words done!");

    }

    public void primeQuestions() {

        long count = 0;

        makeToast(this, "Here we go...");


        //This is important. The DAO needs the Context object for DB access
        daoWords = new WordsDAO(this);
        daoQuestions = new QuestionsDAO(this);

        try {
            //Get an array list of words
            words = daoWords.list();
        } catch (Exception e) {
            makeToast(this, "Failed on daoWords.list()");
            System.out.print( e.toString());
            e.printStackTrace();
        }

        for (WordDTO word : words) {
            QuestionDTO question = new QuestionDTO();

            question.setWord(word.getWord());
            question.setDescription("This is a spelling question.");
            question.setText("How do you spell the word '" + word.getWord() + "'?");
            question.setWordId(word.getWordId());
            try {
                count += daoQuestions.create(question);
                System.out.println(question.getText() );
                //Log.d(TAG, "Created question: " + question.getText() );
            } catch (Exception e) {
                //makeToast(this, e.toString());
                System.out.println( e.toString());

                e.printStackTrace();
            }

        }
        //
        makeToast(this, "Call to PrimeQuestions done!");

    }

    public void primeMe() {
        //
        long result = 0;

        //
        makeToast(this, "Here we go...");

        //This is important. The DAO needs the Context object for DB access
        daoPeople = new PeopleDAO(this);

        PersonDTO dto = new PersonDTO();

        dto.setFirstName("Brian");
        dto.setLastName("Bachmeyer");
        dto.setBirthDate("1-JAN-1970");

        try {
            result = daoPeople.create(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        makeToast(this, "The result is " + result);

    }

    private void toastWords() {
        IWordsDAO dao = new WordsDAO(this);
        ArrayList<WordDTO> list = null;
        int count = 0;
        try {
            list = dao.list();
            count = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        makeToast(this, "There are " + count + " records in the words table.");
    }

    private void toastQuestions() {
        IQuestionsDAO dao = new QuestionsDAO(this);
        ArrayList<QuestionDTO> list = null;
        int count = 0;

        try {
            list = dao.list();

            count = list.size();

        } catch (Exception e) {
            System.out.print( e.toString() );
            e.printStackTrace();
        }
        makeToast(this, "There are " + count + " records in the questions table.");

    }

    public void toastAnswers() {
        int correct = 0;
        int incorrect = 0;

        IAnswersDAO dao = new AnswersDAO(this);
        ArrayList<AnswerDTO> list = null;
        int count = 0;
        try {
            list = dao.list();
            count = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        makeToast(this, "There are " + count + " records in the answers table.");

        for (AnswerDTO dto : list){
            if ( dto.getGrade().equals("1") ){
                correct++;
            }
            if ( dto.getGrade().equals("0") ){
                incorrect++;
            }
        }
        //
        makeToast(this, "There are " + correct + " correct answers.");
        //
        makeToast(this, "There are " + incorrect + " incorrect answers.");

    }

    private String[] makeDolch() {
        String[] words = {
                "the",
                "to",
                "and",
                "he",
                "a",
                "I",
                "you",
                "it",
                "of",
                "in",
                "was",
                "said",
                "his",
                "that",
                "she",
                "for",
                "on",
                "they",
                "but",
                "had",
                "at",
                "him",
                "with",
                "up",
                "all",
                "look",
                "is",
                "her",
                "there",
                "some",
                "out",
                "as",
                "be",
                "have",
                "go",
                "we",
                "am",
                "then",
                "little",
                "down",
                "do",
                "can",
                "could",
                "when",
                "did",
                "what",
                "so",
                "see",
                "not",
                "were",
                "get",
                "them",
                "like",
                "one",
                "this",
                "my",
                "would",
                "me",
                "will",
                "yes",
                "big",
                "went",
                "are",
                "come",
                "if",
                "now",
                "long",
                "no",
                "came",
                "ask",
                "very",
                "an",
                "over",
                "your",
                "its",
                "ride",
                "into",
                "just",
                "blue",
                "red",
                "from",
                "good",
                "any",
                "about",
                "around",
                "want",
                "don’t",
                "how",
                "right",
                "put",
                "too",
                "got",
                "take",
                "where",
                "every",
                "pretty",
                "jump",
                "green",
                "four",
                "away",
                "old",
                "by",
                "their",
                "here",
                "saw",
                "call",
                "after",
                "well",
                "think",
                "ran",
                "let",
                "help",
                "make",
                "going",
                "sleep",
                "brown",
                "yellow",
                "five",
                "six",
                "walk",
                "two",
                "or",
                "before",
                "eat",
                "again",
                "play",
                "who",
                "been",
                "may",
                "stop",
                "off",
                "right",
                "never",
                "seven",
                "eight",
                "cold",
                "today",
                "fly",
                "myself",
                "round",
                "tell",
                "much",
                "keep",
                "give",
                "work",
                "first",
                "try",
                "new",
                "must",
                "start",
                "black",
                "white",
                "ten",
                "does",
                "bring",
                "goes",
                "write",
                "always",
                "drink",
                "once",
                "soon",
                "made",
                "run",
                "gave",
                "open",
                "has",
                "find",
                "only",
                "us",
                "three",
                "our",
                "better",
                "hold",
                "buy",
                "funny",
                "warm",
                "ate",
                "full",
                "those",
                "done",
                "use",
                "fast",
                "say",
                "light",
                "pick",
                "hurt",
                "pull",
                "cut",
                "kind",
                "both",
                "sit",
                "which",
                "fall",
                "carry",
                "small",
                "under",
                "read",
                "why",
                "own",
                "found",
                "wash",
                "show",
                "hot",
                "because",
                "far",
                "live",
                "draw",
                "clean",
                "grow",
                "best",
                "upon",
                "these",
                "sing",
                "together",
                "please",
                "thank",
                "wish",
                "many",
                "shall",
                "laugh"
        };

        return words;
    }

//
//
//    private void querySetGetGo() {
//        System.out.println("START querySetGetGo()");
//
//        //ProximalRestClient.get("statuses/public_timeline.json", null, new JsonHttpResponseHandler() {
//        SetGetGoClient.get("", null, new JsonHttpResponseHandler() {
//
//            @Override
//            public void onFailure(int statusCode,
//                                  org.apache.http.Header[] headers,
//                                  java.lang.Throwable throwable,
//                                  org.json.JSONObject errorResponse) {
//                System.out.println("fail 1");
//
//            }
//
//            @Override
//            public void onFailure(int statusCode,
//                                  org.apache.http.Header[] headers,
//                                  java.lang.Throwable throwable,
//                                  org.json.JSONArray errorResponse) {
//                System.out.println("fail 2");
//
//            }
//
//            @Override
//            public void onFailure(int statusCode,
//                                  org.apache.http.Header[] headers,
//                                  java.lang.String responseString,
//                                  java.lang.Throwable throwable) {
//                System.out.println("fail 3");
//
//            }
//
//            @Override
//            public final void onFailure(int statusCode,
//                                        org.apache.http.Header[] headers,
//                                        byte[] responseBytes,
//                                        java.lang.Throwable throwable) {
//                System.out.println("Status code: " + statusCode);
//                System.out.println("Throwable: " + throwable.toString());
//                for (int i = 0; i < headers.length; i++) {
//                    System.out.println("Header: " + headers[i]);
//
//                }
//
//
//            }
//
//            @Override
//            public void onSuccess(int statusCode,
//                                  org.apache.http.Header[] headers,
//                                  java.lang.String responseString) {
//                System.out.println("success 1");
//                System.out.println("Status code: " + statusCode);
//                for (int i = 0; i < headers.length; i++) {
//                    System.out.println("Header: " + headers[i]);
//
//                }
//                System.out.println("Response string: " + responseString);
//
//
//                txtWord.setText(responseString);
//
//
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                // If the response is JSONObject instead of expected JSONArray
//                System.out.println("JSONObject success");
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
//
//                System.out.println("JSONArray success");
//
//                try {
//
//                    // Pull out the first event on the public timeline
//                    JSONObject firstEvent = (JSONObject) timeline.get(0);
//                    String tweetText = firstEvent.getString("text");
//
//                    // Do something with the response
//                    System.out.println(tweetText);
//
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//            }
//
//        });
//
//        System.out.println("END getPublicTimeLine()");
//
//    }
//
//
//    private void giveMeWord(){
//        WordDTO word;
//        word = service.getNewWord();
//
//        txtWord.setText(word.getWord());
//
//    }
//
//    private void giveMeWord(int level){
//        WordDTO word;
//
//        //word = service.getNewWord(level);
//
//        //txtWord.setText(word.getWord());
//
//    }
//
//    private void queryBooks(String searchString) {
//
//        // Prepare your search string to be put in a URL
//        // It might have reserved characters or something
//        String urlString = "";
//        try {
//            urlString = URLEncoder.encode(searchString, "UTF-8");
//
//        } catch (UnsupportedEncodingException e) {
//
//            // if this fails for some reason, let the user know why
//            e.printStackTrace();
//            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//        // Create a client to perform networking
//        AsyncHttpClient client = new AsyncHttpClient();
//
//        // Have the client get a JSONArray of data
//        // and define how to respond
//        client.get(QUERY_URL + urlString,
//                new JsonHttpResponseHandler() {
//
//                    @Override
//                    public void onSuccess(JSONObject jsonObject) {
//                        // Display a "Toast" message
//                        // to announce your success
//                        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
//
//                        // 8. For now, just log results
//                        Log.d("omg android", jsonObject.toString());
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
//                        // Display a "Toast" message
//                        // to announce the failure
//                        Toast.makeText(getApplicationContext(), "Error: " + statusCode + " " + throwable.getMessage(), Toast.LENGTH_LONG).show();
//
//                        // Log error message
//                        // to help solve any problems
//                        Log.e("omg android", statusCode + " " + throwable.getMessage());
//                    }
//
//                });
//    }
//
//
//    private void queryHeroku(String what) {
//
//        AsyncHttpClient client = new AsyncHttpClient();
//
//        client.get(HEROKU_URL, new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//                System.out.println("HELLO");
//
//                System.out.println("Status code: " + statusCode);
//
//                for (int i=0;i<headers.length;i++){
//                    System.out.println("Headers: " + headers[i]);
//                }
//
//                for (int j=0;j<responseBody.length;j++){
//                    System.out.println("Response body: " + responseBody[j]);
//
//                }
//
//                System.out.println(responseBody.toString());
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
//                    error)
//            {
//                error.printStackTrace(System.out);
//            }
//
//        });
//
//    }
//
//    private void queryGoogle(){
//
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get("http://www.google.com", new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onStart() {
//                // called before request is started
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
//                // called when response HTTP status is "200 OK"
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
//                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//            }
//
//            //@Override
//            public void onRetry(int retryNo) {
//                // called when request is retried
//            }
//        });
//
//    }
//
//    public void getPublicTimeline() throws JSONException {
//        System.out.println("START getPublicTimeLine()");
//
//        //ProximalRestClient.get("statuses/public_timeline.json", null, new JsonHttpResponseHandler() {
//        ProximalRestClient.get("", null, new JsonHttpResponseHandler() {
//
//            @Override
//            public void onFailure(int statusCode,
//                                  org.apache.http.Header[] headers,
//                                  java.lang.Throwable throwable,
//                                  org.json.JSONObject errorResponse) {
//                System.out.println("fail 1");
//
//            }
//
//            @Override
//            public void onFailure(int statusCode,
//                                  org.apache.http.Header[] headers,
//                                  java.lang.Throwable throwable,
//                                  org.json.JSONArray errorResponse) {
//                System.out.println("fail 2");
//
//            }
//
//            @Override
//            public void onFailure(int statusCode,
//                                  org.apache.http.Header[] headers,
//                                  java.lang.String responseString,
//                                  java.lang.Throwable throwable) {
//                System.out.println("fail 3");
//
//            }
//
//            @Override
//            public final void onFailure(int statusCode,
//                                        org.apache.http.Header[] headers,
//                                        byte[] responseBytes,
//                                        java.lang.Throwable throwable) {
//                System.out.println("Status code: " + statusCode);
//                System.out.println("Throwable: " + throwable.toString());
//                for (int i = 0; i < headers.length; i++) {
//                    System.out.println("Header: " + headers[i]);
//
//                }
//
//
//            }
//
//            @Override
//            public void onSuccess(int statusCode,
//                                  org.apache.http.Header[] headers,
//                                  java.lang.String responseString) {
//                System.out.println("success 1");
//                System.out.println("Status code: " + statusCode);
//                for (int i = 0; i < headers.length; i++) {
//                    System.out.println("Header: " + headers[i]);
//
//                }
//                System.out.println("Response string: " + responseString);
//
//
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                // If the response is JSONObject instead of expected JSONArray
//                System.out.println("JSONObject success");
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
//
//                System.out.println("JSONArray success");
//
//                try {
//
//                    // Pull out the first event on the public timeline
//                    JSONObject firstEvent = (JSONObject) timeline.get(0);
//                    String tweetText = firstEvent.getString("text");
//
//                    // Do something with the response
//                    System.out.println(tweetText);
//
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//            }
//
//        });
//
//        System.out.println("END getPublicTimeLine()");
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.welcome, menu);
//        return true;
//    }

}
