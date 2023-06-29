<h1 align="center">Project of Ingegneria del Software: Master of Renaissance</h1>
<h3 align="center">This project is the work of three Computer Science students from Politecnico di Milano</h3>
<body>
    <h4>Our starting goal was to complete :</h4>
    <div>
    <pre>
    <ul>
        <li>
            full game implementation;
        </li>
        <li>
            CLI implementation;
        </li>
        <li>
            GUI implementation;
        </li>
        <li>
            multiple games implementation;
        </li>
        <li>
            disconnection management.
        </li>
    </ul>
    </pre>
</div>
<h4>Final project's implemented functionalities :</h4>
<div>
    <pre>
    <ul>
        <li>
            full game implementation ✅;
        </li>
        <li>
            CLI implementation ✅;
        </li>
        <li>
            GUI implementation 🚧;
        </li>
        <li>
            multiple games implementation ✅;
        </li>
        <li>
            disconnection management ✅.
        </li>
    </ul>
    </pre>
</div>

<h4>Work subdivision :</h4>
<div>
    <ul>
        <li>
            The model was made by Michele Bosio and Jasco Chen;
        </li>
        <li>
            The single player functionality was made by Michele Bosio and Jasco Chen;
        </li>
        <li>
            The network was fully implemented by Michele Bosio;
        </li>
        <li>
            The CLI was made by Michele Bosio and Jasco Chen;
        </li>
        <li>
            The graphical part of the GUI was assigned to Fabio Berzoini while the logical part was assigned to Michele Bosio. We tried to complete the GUI for the final date, however, due to some problems within the group, we didn't make it in time;
        </li>
        <li>
            Reconnection and multiple games additional functionality were made by Michele Bosio;
        </li>
    </ul>
</div>
<h4>Project main parts :</h4>
<div>
    <ul>
        <li>
            Multiple Games: in the network section we thought to make a lobby system: when someone joins the game he's automatically 
            put in a lobby whit the number of players he wants to play with. If that lobby doesn't exists the system creates a new 
            one;
        </li>
        <li>
            Reconnection : using the lobby system we got facilitated while developing the reconnection functionality.
            This because every player is assigned to a specific lobby and when a disconnected player enters the game he's automatically
            linked to the that lobby, which contains all of the information useful for him to carry on whit his game; 
        </li>
    </ul>
</div>
<div>
    <h3>To properly run the jar follow this example : </h3>
    <pre>java -jar AM14.jar [server/cliclient/guiclient] [port to which connect] [IP to connect (if clients)]
             ip from localhost : 127.0.0.1 port : YOUR CHOICE</pre>
    You can find the AM14.jar file in deliverables folder.
</div>
</body>
