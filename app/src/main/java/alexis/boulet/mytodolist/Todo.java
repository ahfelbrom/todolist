package alexis.boulet.mytodolist;

import java.util.ArrayList;
import java.util.List;

// La classe est finale, car un singleton n'est pas censé avoir d'héritier.
public final class Todo {

    // L'utilisation du mot clé volatile, en Java version 5 et supérieure,
    // permet d'éviter le cas où "Singleton.instance" est non nul,
    // mais pas encore "réellement" instancié.
    // De Java version 1.2 à 1.4, il est possible d'utiliser la classe ThreadLocal.
    private static volatile Todo instance = null;

    private List<String> todoList;

    /**
     * Constructeur de l'objet.
     */
    private Todo() {
        super();
    }

    /**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */
    public final static Todo getInstance() {
        if (Todo.instance == null) {
            synchronized(Todo.class) {
                if (Todo.instance == null) {
                    Todo.instance = new Todo();
                    Todo.instance.todoList = new ArrayList<String>();
                }
            }
        }
        return Todo.instance;
    }

    public final List<String> getTodoList()
    {
        return Todo.getInstance().todoList;
    }

    public final void addToDo(String todo)
    {
        this.todoList.add(todo);
    }

    public final boolean cleanTodo()
    {
        this.todoList.clear();

        if (this.todoList.isEmpty())
        {
            return true;
        }
        return false;
    }
}