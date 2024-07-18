package sql.connection; // This speceifies the package that this Java file belongs to
                        // Used to establish a connection to a database
import java.sql.*;      // Importing the classes from java.sql package to enable working with databases
                        // This import statement allows the use of the following classes
                        /* This import brings in all the classes from the java.sql package, which provides the necessary functionality
                           to connect to, query, and interact with SQL database in Java.*/
import java.util.Scanner; // Importing the Scanner class from the java.util package to enable reading user input from the console.


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 *
 * @author Diego Gosalvez
 * ID - 2023192
 * Repeat CA
 * Introduction to Programming
 * 19-07-2024
 */
//DATABASE CONNECTION   
public class SqlConnection { 
// Main method to establish a database connection.
    public static void main(String[] args) {
        // Create a Scanner object to read user input from the console
        Scanner myKB = new Scanner(System.in);
        // Connect to the database
        // TODO code application logic here
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Load the MySQL JDBC driver 
        /* The MySQL JDBC driver is a Java library file, that allows Java applications to connect to my MySQL database.
        This enabling to perform database operations such as queryng data, inserting records and updating information*/
        Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/nfl", "root", "1989-GosalvezDiego");
        /* This the connection to the MySQL databse along with the username anda password.
        This connection URL shows the server and the port that I want to connect*/
        // Establish a connection to the "nfl" database using the specified credentials
        System.out.println("Connected to the system");
        // Print a message indicating succesful connection
        //Statement object (Statement stmt = con.createStatement();) before executing the query. 
        // Create a Statement object
        Statement stmt = con.createStatement();
        // This command going to start the main menu of the system
        console_menu_system(stmt);
        } catch (SQLException | ClassNotFoundException SQLex) {
          System.out.println("Connection failed");
          // Handle exceptions related to database connection
        }
 }      
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    /*To clarify, I have put all 14 queries in one menu. Seven of these queries can be varied by the user through keyboard input. 
    The user has to type the correct query to access the information. If the user types the wrong information, 
    the program will show an error message.
    The following queries can be varied by the user: 3, 4, 6, 11, 12, 13, 14*/
    
    
    // IMPLEMENT A MENU SYSTEM  
    public static void console_menu_system(Statement stmt)throws SQLException {
    Scanner myKB = new Scanner(System.in);
    int option;
    // Declare an integer variable to store the user's menu choice
    // implement menu options like query data, insert records and names.
    
    while (true) {
        System.out.println("//// CONSOLE MENU SYSTEM ////");// Display the main menu header
        System.out.println("Please select an option:"); // Prompt the user to choose an option
        System.out.println("1. Option 1 (List all captains ID with their first name, last name and team)");// Option 1: List captains information
        System.out.println("2. Option 2 (List all unique positions found in the database)");// Option 2: List unique player positions
        System.out.println("3. Option 3 (List of the players name ordered alphabetically in ascending order by their name and lastname, for a particular team)");// Option 3: List player names alphabetically
        System.out.println("4. Option 4 (Searching a football players team with their last name)");// Option 4: Searching typing only the last name
        System.out.println("5. Option 5 (Provide the list with all the details of the players of the teams that lost the first matchday)");// Option 5: List players from losing teams
        System.out.println("6. Option 6 (Select a team to see all the players with injuries)");// Option 6: List all of the football players with injuries
        System.out.println("7. Option 7 (How many football players have had more than one injury)");// Option 7: Identify players with multiple injuries
        System.out.println("8. Option 8 (Display a list of the most common injuries and order them by ascending order)");// Option 8: List common injuries
        System.out.println("9. Option 9 (Which match date has more draws)");// Option 9: Identify matchday with more draws
        System.out.println("10. Option 10 (Count how many player have a low-medium-high skill level)");// Option 10: Count players by skill level
        System.out.println("11. Option 11 (Choose a team name showing the football player information with first name, last name, position and skill)");// Option 11: football players information
        System.out.println("12. Option 12 (List the fixture dates that already happened for a particular team)");// Option 12: Shows the 4 matches date that the all of team have been playing
        System.out.println("13. Option 13 (List teams' information about the oficial name, state, stadium and coach that belongs to the NFL League)");// Option 13: Shows all information of the twenty teams
        System.out.println("14. Option 14 (Check if a team won, lost, or drew the last match)");// Option 14: results for the last match
        System.out.println("15. Exit");// Option 15: Exit the program
        System.out.print("Your choice: ");// Prompt for user input
                    
        String input = myKB.nextLine();
        // Read user input as a string

            try {
                option = Integer.parseInt(input);
                // Convert the input to an integer
                } catch (NumberFormatException e) {
                    // Handle the case where input is not an integer
                    System.out.println("Invalid choice. Please enter a valid option.");
                    continue;}  
                    // This continues the while loop, redeploying the menu
                    /*In this part I used the statement Try-catch  when the user type an incorrect integer or string as an invalid option the program re-display the
                    same menu until the user types the correct option*/      
            
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
            
                     switch (option){// Handle different menu options based on the user's choice
                     
                         
                        case 1: // Option 1: List captains' information
                            ResultSet rs1 = stmt.executeQuery("SELECT player_id, first_name, last_name, team_name, "
                                    + "captain FROM player WHERE player.captain = 1");
                             // Execute a SQL query to retrieve information about players who are captains
                             while (rs1.next()) {
                                // Iterate through the result set
                                System.out.println(rs1.getInt(1) + " / " + rs1.getString(2) 
                                 + " / " + rs1.getString(3) + " /  " + rs1.getString(4) + " / " + rs1.getBoolean(5));
                                 // Print details of each captain (player ID, first name, last name, team name, and captain status)
                                } break;
                                         
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                        
                        
                        case 2: // Option 2: List unique player positions
                            ResultSet rs2 = stmt.executeQuery("SELECT DISTINCT position FROM player");
                            // Execute a SQL query to retrieve distinct player positions
                            while (rs2.next()) {
                            // Iterate through the result set    
                               System.out.println(rs2.getString(1));
                               // Print each unique player position
                               } break;


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        case 3: // Option 3: List players' names ordered alphabetically by their last name
                            // Prompt the user to enter the team name 
                            // THIS QUERY CAN BE VARIED BY THE USER    
                            System.out.print("Enter the team name: ");
                            String teamName = myKB.nextLine();
                            // Read the user's input and store it in the teamName variable 
                            /*Execute a SQL query to retrieve player information for the specified team. The query select last_name, first_name
                            and team_name columns from the player table.
                            The results are ordered alphabetically by last name in ascending order.*/
                            ResultSet rs3 = stmt.executeQuery(" SELECT last_name, first_name, team_name FROM player "
                                    + "WHERE team_name = '" + teamName + "' ORDER by last_name ASC");
                            if (!rs3.isBeforeFirst()) {
                               // check if the result is empty, no players found for the specified team
                               System.out.println("No team found with the name: " + teamName + ". Please enter a valid team name.");
                               }// Execute a SQL query to retrieve player information of the team. As the instruction to list a particular team
                                // Ordered by last name in ascending order
                            while (rs3.next()) {
                                // Iterate through the result set    
                                System.out.println(rs3.getString(1) + " / " + rs3.getString(2) + " / " + rs3.getString(3));
                                // Print each player's last name, first name, and team name
                                } break;   


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                    

                                
                        case 4: // Prompt the user to enter the player's last name 
                            // THIS QUERY CAN BE VARIED BY THE USER  
                            System.out.print("Enter the player's last name: ");
                            String playerLastName = myKB.nextLine();
                            // Read the user's input and store it in the playerLastName variable
                            // Execute a SQL query to retrieve the player's information based on the last name
                            ResultSet rs4 = stmt.executeQuery("SELECT p.first_name, p.last_name, t.team_name "
                                    + "FROM player p "
                                    + "JOIN team t ON p.team_id = t.team_id "
                                    + "WHERE p.last_name = '" + playerLastName + "'");
                             // Check if any players were found with the specified last name

                            if (!rs4.isBeforeFirst()) {   // If no players are found, print an error message
                                System.out.println("No player found with the last name: " + playerLastName);                                           
                                } else {// If players are found, print the player information header
                                  System.out.println("Player Information:");                                                   
                                  while (rs4.next()) {                                                         
                                        // Iterate through the result set and print the player's first name, last name, and team name
                                        System.out.println("First Name: " + rs4.getString(1));
                                        System.out.println("Last Name: " + rs4.getString(2));
                                        System.out.println("Team Name: " + rs4.getString(3));
                                        System.out.println();}
                                  }break;


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        case 5: // Option 5: List players from teams that lost the first matchday
                            // The query involves multiples joins like player, team and match day
                            ResultSet rs5 = stmt.executeQuery("SELECT player.player_id, player.first_name, player.last_name, player.position, player.skill, team.team_name "// identifiers for each player
                                    + "FROM player JOIN team ON player.team_id = team.team_id JOIN match_day ON (player.team_id = match_day.host_team_id OR player.team_id = match_day.guest_team_id) "
                                    + "WHERE (player.team_id = match_day.host_team_id AND match_day.host_team_score < match_day.guest_team_score) OR "
                                    + "(player.team_id = match_day.guest_team_id AND match_day.guest_team_score < match_day.host_team_score) ORDER BY player.skill ASC");
                                    /* The query filters player based on the following conditions: If the team lost either as the host or guest on the first match day.
                                    If the team is the host, its score should be less than guest team's score.
                                    If the team is the guest, its score should be less thann the host team's score.*/
                                    // Execute a SQL query to retrieve player information from teams that lost the first matchday
                            while (rs5.next()) {
                                // Iterate through the result set
                                System.out.println(rs5.getInt(1) + " / " + rs5.getString(2) + " / " + rs5.getString(3)
                                        + " / " + rs5.getString(4) + " / " + rs5.getString(5) + " / " + rs5.getString(6));
                                 // Print details of each player (player ID, first name, last name, position, skill, and team name)
                                } break;
                                
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                


                        case 6: // Option 6: List details of players with injuries
                            // THIS QUERY CAN BE VARIED BY THE USER  
                            System.out.print("Enter the team name: ");
                            teamName = myKB.nextLine();// Read the user's input and store it in the teamName variable
                            // Define the SQL query to retrieve player information along with their injuries for the specified team
                            String sql = "SELECT p.player_id, p.first_name, p.last_name, p.position, p.skill, t.team_name, i.injury_type, i.injury_date "
                                    + "FROM player p "
                                    + "JOIN injuries_rec i ON p.player_id = i.player_id " // Join the injuries_rec table on player_id
                                    + "JOIN team t ON p.team_id = t.team_id "   // Join the team table on team_id
                                    + "WHERE t.team_name = '" + teamName + "' " // Filter the results by the specified team name
                                    + "ORDER BY p.player_id"; // Order the results by player_id
                            ResultSet rs6 = stmt.executeQuery(sql); // Execute the SQL query
                            if (!rs6.isBeforeFirst()) {
                                // Check if the result set is empty
                                System.out.println("No injuries found for the team: " + teamName); // Print a message if no injuries are found
                                } else {
                                System.out.println("Injuries for the " + teamName + " team:"); // Print a header for the results
                                while (rs6.next()) {
                                    // Print details of each injured player (player ID, first name, last name, position, skill level, injury type, and injury date
                                    System.out.println("Player ID: " + rs6.getInt(1));
                                    System.out.println("First Name: " + rs6.getString(2));
                                    System.out.println("Last Name: " + rs6.getString(3));
                                    System.out.println("Position: " + rs6.getString(4));
                                    System.out.println("Skill Level: " + rs6.getString(5));
                                    System.out.println("Injury Type: " + rs6.getString(7));
                                    System.out.println("Injury Date: " + rs6.getDate(8));
                                    System.out.println();
                                    }
                                }
                            break;


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                            


                        case 7:// Part "a" Football player with more than one injury
                            ResultSet rs7a = stmt.executeQuery("SELECT COUNT(DISTINCT player_id) AS players_with_more_than_one_injury FROM (SELECT player.player_id FROM player JOIN injuries_rec ir ON player.player_id = ir.player_id "
                                    // only the players with injuries are considered
                                    + "GROUP BY player.player_id HAVING COUNT(ir.injury_id) > 1) AS players_with_more_than_one_injury");
                                    // conditions filters players who have more than one injury
                            while (rs7a.next()) {
                                //The second query retrieves detailed information about players with multiple injuries.
                                System.out.println("Players with More Than One Injury: " + rs7a.getInt(1));
                                }
                            // Part "b" Details of the football player injury
                            ResultSet rs7b = stmt.executeQuery("SELECT player.player_id, player.first_name, player.last_name, team.team_name, team.state, injuries_rec.injury_type, injuries_rec.injury_date "
                                    + "FROM player JOIN team ON player.team_id = team.team_id JOIN injuries_rec ON player.player_id = injuries_rec.player_id "
                                    + "WHERE player.player_id IN (SELECT player_id FROM injuries_rec GROUP BY player_id HAVING COUNT(*) > 1) GROUP BY player.player_id, "
                                    + "player.first_name, player.last_name, team.team_name, team.state, injuries_rec.injury_type, injuries_rec.injury_date");
                                    //clause groups the results by player ID, first name, last name, team name, state, injury type, and injury date.
                             while (rs7b.next()) {
                             System.out.println(rs7b.getInt(1) + " / " + rs7b.getString(2) + " / " + rs7b.getString(3) + " / " + rs7b.getString(4) + " / "
                                     + rs7b.getString(5) + " / " + rs7b.getString(6) + " / " + rs7b.getDate(7));
                             // Result set and prints the details of each players injuries
                             } break;   

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        case 8:// Option 8: List the most common injuries
                            ResultSet rs8 = stmt.executeQuery("SELECT injury_type, COUNT(*) AS injury_count FROM injuries_rec GROUP BY injury_type ORDER BY injury_count ASC");
                        // Execute a SQL query to count occurrences of each injury type
                            while (rs8.next()) {
                               // Iterate through the result set
                               System.out.println("Injury Type: " + rs8.getString(1) + ", Injury Count: " + rs8.getInt(2));
                               // Print each injury type and its corresponding count
                               } break;  

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



                        case 9:// query retrieves the fixture date and the count of draws
                            // Part "a" Which team had more draws
                            ResultSet rs9a = stmt.executeQuery("SELECT fixture_date, COUNT(*) AS draw_count FROM match_day WHERE host_team_score = guest_team_score GROUP BY fixture_date ORDER BY draw_count DESC LIMIT 1");
                            // It groups the results by fixture date
                            while (rs9a.next()) {
                                 System.out.println("Fixture Date: " + rs9a.getDate(1) + ", Draw Count: " + rs9a.getInt(2));
                                 }//ensures that we get the fixture date with the highest draw count.
                                 // Part "b" Wich fixture date had more draws
                                ResultSet rs9b = stmt.executeQuery("SELECT m.fixture_date, th.team_name AS host_team, m.host_team_score, tg.team_name AS guest_team, m.guest_team_score "
                                        + "FROM match_day m JOIN team th ON m.host_team_id = th.team_id JOIN team tg ON m.guest_team_id = tg.team_id "
                                        + "WHERE m.fixture_date = (SELECT fixture_date FROM match_day WHERE host_team_score = guest_team_score GROUP BY fixture_date ORDER BY COUNT(*) DESC LIMIT 1)");
                                        //The where clause ensures that we consider only the fixture date obtained from the first query.
                                        //The result set contains columns for fixture date, host team name, host team score, guest team name, and guest team score.
                                while (rs9b.next()) {
                                    System.out.println("Fixture Date: " + rs9b.getDate(1) + ", Host Team: " + rs9b.getString(2) + ", Host Team Score: " + rs9b.getInt(3) + ", Guest Team: "
                                            + rs9b.getString(4) + ", Guest Team Score: " + rs9b.getInt(5));
                                    //prints the details of the fixture date and teams
                                    //The result set contains columns for fixture date, host team name, host team score, guest team name, and guest team score
                                    } break; 


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        case 10://The query retrieves information about player skills grouped by team name.
                            ResultSet rs10 = stmt.executeQuery("SELECT team_name, COUNT(CASE WHEN skill = 'low' THEN 1 END) AS low_skill, COUNT(CASE WHEN skill = 'medium' THEN 1 END) AS medium_skill, "
                                    + "COUNT(CASE WHEN skill = 'high' THEN 1 END) AS high_skill FROM player GROUP BY team_name ORDER BY low_skill, medium_skill, high_skill");
                                    // Number of player with low-medium-high skill.
                                    // The order by team_name groups results by team name
                                    // sorts the teams based on the counts in ascending order
                            while (rs10.next()) {// The result set contains columns for team name, low skill count, medium and hig skill.
                                System.out.println("Team Name: " + rs10.getString(1) + ", Low Skill: " + rs10.getInt(2) 
                                        + ", Medium Skill: " + rs10.getInt(3) + ", High Skill: " + rs10.getInt(4));
                                 //prints the details for each team, including skill counts.
                                } break; 

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        case 11:// Prompt the user to enter the team name
                            System.out.println("Enter the team name:");
                            String teamName11 = myKB.nextLine();
                            // Execute a query to retrieve player information for the specified team
                            ResultSet rs11 = stmt.executeQuery("SELECT first_name, last_name, position, skill FROM player WHERE team_name = '" + teamName11 + "'");
                            // Check if any players were found for the team
                            if (!rs11.isBeforeFirst()) {
                               System.out.println("No players found for the team: " + teamName11 + ". Please enter a valid team name.");
                               }
                            // Print player details (if any)
                            while (rs11.next()) {
                                System.out.println(rs11.getString(1) + " / " + rs11.getString(2) + " / " + rs11.getString(3) + " / " + rs11.getString(4));
                                 //Retrieves the values of the second, third and fourth column from the result set 13, the line concatenate the values
                                }break;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                                
                        case 12:// Retrieve fixture dates from the database where the date is before the current date
                            System.out.print("Enter the team name: ");
                            teamName = myKB.nextLine();
                            // Construct the SQL query to retrieve the fixture dates for the specified team
                            sql = "SELECT m.fixture_date "
                                    + "FROM match_day m "
                                    + "JOIN team t ON (m.host_team_id = t.team_id OR m.guest_team_id = t.team_id) "
                                    + "WHERE t.team_name = '" + teamName + "' "
                                    + "AND m.fixture_date < CURDATE() "
                                    + "GROUP BY m.fixture_date";
                            // Execute the SQL query and store the result set in rs12
                            ResultSet rs12 = stmt.executeQuery(sql);
                            // Check if the result set is empty (no matchdays found for the specified team)
                            if (!rs12.isBeforeFirst()) {
                                 // If no matchdays are found, print a message
                                System.out.println("No matchdays found for the team: " + teamName);
                                } else {
                                // If matchdays are found, print a header
                                System.out.println("Matchdays played by the " + teamName + " team:");
                                // Iterate through the result set and print the fixture dates
                                while (rs12.next()) {
                                    System.out.println(rs12.getDate(1));}
                                }break;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



                        case 13:// Retrieve all team information from the 'nfl.team' table
                            System.out.print("Enter the team name: ");
                            teamName = myKB.nextLine();// Read the user's input and store it in the teamName variable
                            // Execute a SQL query to retrieve team information for the specified team
                            ResultSet rs13 = stmt.executeQuery("SELECT * FROM nfl.team WHERE team_name = '" + teamName + "'");
                            // Check if any teams were found for the entered name
                            if (!rs13.isBeforeFirst()) {
                            // If no teams are found, print an error message
                                System.out.println("No team found with the name: " + teamName + ". Please enter a valid team name.");
                                } else {
                                // If a team is found, print the team information header
                                System.out.println("Information for: " + teamName + " team:");
                                while (rs13.next()) {// Iterate through the result set and print the team details
                                    System.out.println(rs13.getInt(1) + " / " + rs13.getString(2) + " / "
                                            + rs13.getString(3) + " / " + rs13.getString(4) + " / " 
                                            + rs13.getString(5));}
                                }break;


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                          
                        case 14:// Prompt the user to enter the team name
                            System.out.println("Enter the team name:");
                            String teamName14 = myKB.nextLine();
                            // Query to determine the last match result for the specified team
                            String query = "SELECT fixture_date, "
                            // the query retrieves the fixture date and the match result, win-loss-draw
                                    + "CASE "// statement to determinate the result based on the host team score and guest team score
                                    + "WHEN (host_team_id = team_id AND host_team_score > guest_team_score) OR (guest_team_id = team_id AND guest_team_score > host_team_score) THEN 'Win' "
                                    + "WHEN (host_team_id = team_id AND host_team_score < guest_team_score) OR (guest_team_id = team_id AND guest_team_score < host_team_score) THEN 'Loss' "
                                    + "ELSE 'Draw' " + "END AS result " + "FROM match_day " + "JOIN team ON (match_day.host_team_id = team.team_id OR match_day.guest_team_id = team.team_id) "
                                    + "WHERE team.team_name = ? " + "ORDER BY fixture_date DESC LIMIT 1";
                            // ensure that most recent match result
                            // The result contain the fixture date and the match result
                            try (PreparedStatement pstmt = stmt.getConnection().prepareStatement(query)) {
                                // Prepare a PreparedStatement with the specified query
                                pstmt.setString(1, teamName14);
                                // Set the value of the first parameter (team name) in the prepared statement
                                try (ResultSet rs = pstmt.executeQuery()) {
                                    // Execute the query and retrieve the result set
                                    if (rs.next()) {
                                        // If there is a result (match data found)
                                        System.out.println("Last match result for " + teamName14 + ": " + rs.getString("result"));
                                        // Print the last match result for the specified team
                                        } else {
                                        // If no result (no match data found)
                                        System.out.println("No match data found for the team: " + teamName14 + ". Please enter a valid team name.");
                                        }
                                    }
                                }break; // End of the try-with-resources block       


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        case 15:// Print a message indicating program exit
                            System.out.println("Exiting program.");
                            // Terminate the program with exit code 0
                            System.exit(0);
                            // the break statement ensures that execution exit the switch block
                            break;    

//////////////////////////////////////////////////////////////////////////////////////////////////////////
                            
                        default:// Print a message indicating an invalid choice
                            System.out.println("Invalid choice. Please enter a valid option.");
                            }
                          }
    }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    

                
               
   


        
    
    
    
    
            
            
            
           
  
     
