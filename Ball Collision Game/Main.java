import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


class Game {
    public static void gamePlay(Dictionary<String,String> coordinates,Dictionary<String ,String> whiteBall, String moves, Integer row,Integer column){
        Integer totalpoints = 0;
        Integer a = 0;
        List<String> playedMoves = new ArrayList<>();
        Dictionary<String,String> whiteBallcoordinate = whiteBall;
        Dictionary<String,String> newcoordinates = coordinates;
        for (String move : moves.split("")){
            playedMoves.add(move);
            if (move.equals("L")){
                if (whiteBallcoordinate.get("*").toString().split("-")[1].equals("0")){
                    if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+column.toString()).toString().equals("W")){
                        if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1)).equals("H")){
                            a=1;
                            break;
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1)).equals("B")) {
                            totalpoints = totalpoints-5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1));
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1)).equals("R")) {
                            totalpoints = totalpoints+10;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1));
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1)).equals("Y")) {
                            totalpoints = totalpoints+5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1));
                        } else {
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1)));
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1));
                        }
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+column.toString()).toString().equals("H")) {
                        a=1;
                        break;
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+column.toString()).toString().equals("B")) {
                        totalpoints = totalpoints-5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+column.toString());
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+column.toString()).toString().equals("R")) {
                        totalpoints = totalpoints+10;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+column.toString());
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+column.toString()).toString().equals("Y")) {
                        totalpoints = totalpoints+5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+column.toString());
                    } else {
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column)));
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+column.toString());
                    }
                } else if (whiteBallcoordinate.get("*").toString().split("-")[1].equals(column.toString())) {
                    if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column-1)).toString().equals("W")){
                        if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0").equals("H")){
                            a=1;
                            break;
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0").equals("B")) {
                            totalpoints = totalpoints-5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0");
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0").equals("R")) {
                            totalpoints = totalpoints +10;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0");
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0").equals("Y")) {
                            totalpoints = totalpoints +5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0");
                        } else {
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0"));
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0");
                        }
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column-1)).toString().equals("H")) {
                        a=1;
                        break;
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column-1)).toString().equals("B")) {
                        totalpoints = totalpoints - 5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column-1).toString());
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column-1)).toString().equals("R")) {
                        totalpoints = totalpoints +10;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column-1).toString());
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column-1)).toString().equals("Y")) {
                        totalpoints = totalpoints +5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column-1).toString());
                    } else {
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column-1).toString()));
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column-1).toString());
                    }
                } else {
                    if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString()).equals("W")){
                        if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString()).equals("H")) {
                            a=1;
                           break;
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString()).equals("B")) {
                            totalpoints = totalpoints -5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString());
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString()).equals("R")) {
                            totalpoints = totalpoints + 10;
                            newcoordinates.put(whiteBallcoordinate.get("*"),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString());
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString()).equals("Y")) {
                            totalpoints = totalpoints + 5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString());
                        } else {
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString()).toString());
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString());
                        }

                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString()).equals("H")) {
                        a=1;
                        break;
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString()).equals("B")) {
                        totalpoints = totalpoints - 5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString());
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString()).equals("R")) {
                        totalpoints = totalpoints + 10;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString());
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString()).equals("Y")) {
                        totalpoints = totalpoints + 5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString());
                    } else {
                        newcoordinates.put(whiteBallcoordinate.get("*"),newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString()));
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString());
                    }
                }

            } else if (move.equals("R")) {
                if (whiteBallcoordinate.get("*").toString().split("-")[1].equals(Integer.toString(column))){
                    if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0").toString().equals("W")){
                        if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1)).equals("H")){
                            a=1;
                            break;
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1)).equals("B")) {
                            totalpoints = totalpoints-5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1));
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1)).equals("R")) {
                            totalpoints = totalpoints+10;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1));
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1)).equals("Y")) {
                            totalpoints = totalpoints+5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1));
                        } else {
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1)));
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1));
                        }
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0").toString().equals("H")) {
                        a=1;
                        break;
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0").toString().equals("B")) {
                        totalpoints = totalpoints-5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0");
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0").toString().equals("R")) {
                        totalpoints = totalpoints+10;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0");
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0").toString().equals("Y")) {
                        totalpoints = totalpoints+5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0");
                    } else {
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0"));
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"0");
                    }
                } else if (whiteBallcoordinate.get("*").toString().split("-")[1].equals("0")) {
                    if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"1").toString().equals("W")){
                        if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column)).equals("H")){
                            a=1;
                            break;
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column)).equals("B")) {
                            totalpoints = totalpoints-5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column));
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column)).equals("R")) {
                            totalpoints = totalpoints +10;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column));
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column)).equals("Y")) {
                            totalpoints = totalpoints +5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column));
                        } else {
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column)));
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(column));
                        }
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"1").toString().equals("H")) {
                        a=1;
                        break;
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"1").toString().equals("B")) {
                        totalpoints = totalpoints - 5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"1");
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"1").toString().equals("R")) {
                        totalpoints = totalpoints +10;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"1");
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"1").toString().equals("Y")) {
                        totalpoints = totalpoints +5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"1");
                    } else {
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"1"));
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+"1");
                    }
                } else {
                    if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString()).equals("W")){
                        if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString()).equals("H")) {
                            a=1;
                            break;
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString()).equals("B")) {
                            totalpoints = totalpoints -5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString());
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString()).equals("R")) {
                            totalpoints = totalpoints + 10;
                            newcoordinates.put(whiteBallcoordinate.get("*"),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString());
                        } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString()).equals("Y")) {
                            totalpoints = totalpoints + 5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString());
                        } else {
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString()).toString());
                            whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])-1).toString());
                        }

                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString()).equals("H")) {
                        a=1;
                        break;
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString()).equals("B")) {
                        totalpoints = totalpoints - 5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString());
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString()).equals("R")) {
                        totalpoints = totalpoints + 10;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString());
                    } else if (newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString()).equals("Y")) {
                        totalpoints = totalpoints + 5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString());
                    } else {
                        newcoordinates.put(whiteBallcoordinate.get("*"),newcoordinates.get(whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString()));
                        whiteBallcoordinate.put("*",whiteBallcoordinate.get("*").toString().split("-")[0]+"-"+Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[1])+1).toString());
                    }
                }
                
            } else if (move.equals("U")) {
                if (whiteBallcoordinate.get("*").toString().split("-")[0].equals("0")){
                    if (newcoordinates.get(Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("W")){
                        if (newcoordinates.get("1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("H")){
                            a=1;
                            break;
                        } else if (newcoordinates.get("1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("B")) {
                            totalpoints = totalpoints-5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*","1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else if (newcoordinates.get("1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("R")) {
                            totalpoints = totalpoints+10;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*","1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else if (newcoordinates.get("1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("Y")) {
                            totalpoints = totalpoints+5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*","1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else {
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get("1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]));
                            whiteBallcoordinate.put("*","1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        }
                    } else if (newcoordinates.get(Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("H")) {
                        a=1;
                        break;
                    } else if (newcoordinates.get(Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("B")) {
                        totalpoints = totalpoints-5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else if (newcoordinates.get(Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("R")) {
                        totalpoints = totalpoints+10;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else if (newcoordinates.get(Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("Y")) {
                        totalpoints = totalpoints+5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else {
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]));
                        whiteBallcoordinate.put("*",Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    }
                } else if (whiteBallcoordinate.get("*").toString().split("-")[0].equals(Integer.toString(row))) {
                    if (newcoordinates.get(Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("W")){
                        if (newcoordinates.get("0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("H")){
                            a=1;
                            break;
                        } else if (newcoordinates.get("0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("B")) {
                            totalpoints = totalpoints-5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*","0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else if (newcoordinates.get("0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("R")) {
                            totalpoints = totalpoints +10;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*","0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else if (newcoordinates.get("0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("Y")) {
                            totalpoints = totalpoints +5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*","0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else {
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get("0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]));
                            whiteBallcoordinate.put("*","0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        }
                    } else if (newcoordinates.get(Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("H")) {
                        a=1;
                        break;
                    } else if (newcoordinates.get(Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("B")) {
                        totalpoints = totalpoints - 5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else if (newcoordinates.get(Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("R")) {
                        totalpoints = totalpoints +10;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else if (newcoordinates.get(Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("Y")) {
                        totalpoints = totalpoints +5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else {
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]));
                        whiteBallcoordinate.put("*",Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    }
                } else {
                    if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("W")){
                        if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("H")) {
                            a=1;
                            break;
                        } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("B")) {
                            totalpoints = totalpoints -5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("R")) {
                            totalpoints = totalpoints + 10;
                            newcoordinates.put(whiteBallcoordinate.get("*"),"X");
                            whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("Y")) {
                            totalpoints = totalpoints + 5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else {
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]));
                            whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        }

                    } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("H")) {
                        a=1;
                        break;
                    } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("B")) {
                        totalpoints = totalpoints - 5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("R")) {
                        totalpoints = totalpoints + 10;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("Y")) {
                        totalpoints = totalpoints + 5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else {
                        newcoordinates.put(whiteBallcoordinate.get("*"),newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]));
                        whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    }
                }
            } else if (move.equals("D")) {
                if (whiteBallcoordinate.get("*").toString().split("-")[0].equals(Integer.toString(row))){
                    if (newcoordinates.get("0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("W")){
                        if (newcoordinates.get(Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("H")){
                            a=1;
                            break;
                        } else if (newcoordinates.get(Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("B")) {
                            totalpoints = totalpoints-5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else if (newcoordinates.get(Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("R")) {
                            totalpoints = totalpoints+10;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else if (newcoordinates.get(Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("Y")) {
                            totalpoints = totalpoints+5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else {
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]));
                            whiteBallcoordinate.put("*",Integer.toString(row-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        }
                    } else if (newcoordinates.get("0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("H")) {
                        a=1;
                        break;
                    } else if (newcoordinates.get("0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("B")) {
                        totalpoints = totalpoints-5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*","0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else if (newcoordinates.get("0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("R")) {
                        totalpoints = totalpoints+10;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*","0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else if (newcoordinates.get("0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("Y")) {
                        totalpoints = totalpoints+5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*","0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else {
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get("0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]));
                        whiteBallcoordinate.put("*","0"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    }
                } else if (whiteBallcoordinate.get("*").toString().split("-")[0].equals("0")) {
                    if (newcoordinates.get("1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("W")){
                        if (newcoordinates.get(Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("H")){
                            a=1;
                            break;
                        } else if (newcoordinates.get(Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("B")) {
                            totalpoints = totalpoints-5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else if (newcoordinates.get(Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("R")) {
                            totalpoints = totalpoints +10;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else if (newcoordinates.get(Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("Y")) {
                            totalpoints = totalpoints +5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else {
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]));
                            whiteBallcoordinate.put("*",Integer.toString(row)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        }
                    } else if (newcoordinates.get("1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("H")) {
                        a= 1;
                        break;
                    } else if (newcoordinates.get("1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("B")) {
                        totalpoints = totalpoints - 5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*","1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else if (newcoordinates.get("1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("R")) {
                        totalpoints = totalpoints +10;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*","1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else if (newcoordinates.get("1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).toString().equals("Y")) {
                        totalpoints = totalpoints +5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*","1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else {
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get("1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]));
                        whiteBallcoordinate.put("*","1"+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    }
                } else {
                    if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("W")){
                        if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("H")) {
                            a = 1;
                            break;
                        } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("B")) {
                            totalpoints = totalpoints -5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("R")) {
                            totalpoints = totalpoints + 10;
                            newcoordinates.put(whiteBallcoordinate.get("*"),"X");
                            whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("Y")) {
                            totalpoints = totalpoints + 5;
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                            whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        } else {
                            newcoordinates.put(whiteBallcoordinate.get("*").toString(),newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]));
                            whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])-1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                        }

                    } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("H")) {
                        a = 1;
                        break;
                    } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("B")) {
                        totalpoints = totalpoints - 5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("R")) {
                        totalpoints = totalpoints + 10;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else if (newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]).equals("Y")) {
                        totalpoints = totalpoints + 5;
                        newcoordinates.put(whiteBallcoordinate.get("*").toString(),"X");
                        whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    } else {
                        newcoordinates.put(whiteBallcoordinate.get("*"),newcoordinates.get(Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]));
                        whiteBallcoordinate.put("*",Integer.toString(Integer.parseInt(whiteBallcoordinate.get("*").toString().split("-")[0])+1)+"-"+whiteBallcoordinate.get("*").toString().split("-")[1]);
                    }
                }
            }
        }

        newcoordinates.put(whiteBallcoordinate.get("*"),"*");
        if (a.equals(0)) {
            System.out.println("\nYour movement is:");
            FileOutput.writeToFile("output.txt","\nYour movement is:",true,true);
            System.out.println(moves+"\n");
            FileOutput.writeToFile("output.txt",moves+"\n",true,true);
            System.out.println("Your output is:");
            FileOutput.writeToFile("output.txt","Your output is:",true,true);
            for (int i = 0; i <= row; i++) {
                for (int x = 0; x <= column; x++) {
                    System.out.print(newcoordinates.get(Integer.toString(i) + "-" + Integer.toString(x)) + " ");
                    FileOutput.writeToFile("output.txt", newcoordinates.get(Integer.toString(i) + "-" + Integer.toString(x)) + " ", true, false);
                }
                System.out.println();
                FileOutput.writeToFile("output.txt", "", true, true);
            }
            System.out.println("\nScore: " + totalpoints);
            FileOutput.writeToFile("output.txt", "\nScore: " + totalpoints, true, true);
        }else {
            System.out.println("\nYour movement is:");
            FileOutput.writeToFile("output.txt","\nYour movement is:",true,true);
            for (String letter : playedMoves){
                System.out.print(letter);
                FileOutput.writeToFile("output.txt",letter,true,false);
            }
            System.out.println("\n\nYour output is:");
            FileOutput.writeToFile("output.txt","\n\nYour output is:",true,true);
            newcoordinates.put(whiteBallcoordinate.get("*")," ");
            for (int i = 0; i <= row; i++) {
                for (int x = 0; x <= column; x++) {
                    System.out.print(newcoordinates.get(Integer.toString(i) + "-" + Integer.toString(x)) + " ");
                    FileOutput.writeToFile("output.txt", newcoordinates.get(Integer.toString(i) + "-" + Integer.toString(x)) + " ", true, false);
                }
                System.out.println();
                FileOutput.writeToFile("output.txt", "", true, true);
            }
            System.out.println("\nGame Over!\nScore: " + totalpoints);
            FileOutput.writeToFile("output.txt", "\nGame Over!\nScore: " + totalpoints, true, true);
        }
        }
    }
class FileInput {
    public static String[] readFile(String path, boolean discardEmptyLines, boolean trim) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            if (discardEmptyLines) {
                lines.removeIf(line -> line.trim().equals(""));
            }
            if (trim) {
                lines.replaceAll(String::trim);
            }
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
class FileOutput {
    public static void writeToFile(String path, String content, boolean append, boolean newLine) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(path, append));
            ps.print(content + (newLine ? "\n" : ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.flush();
                ps.close();
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Integer row = 0;
        Integer column = 0;
        Integer columncounter = 0;
        Dictionary<String,String> coordinates = new Hashtable<>();
        Dictionary<String,String> whiteBall = new Hashtable<>();
        String[] boardtxt = FileInput.readFile(args[0],true,true);
        String[] movetxt = FileInput.readFile(args[1],true,true);
        System.out.println("Game board:");
        FileOutput.writeToFile("output.txt","Game board:",true,true);
        for (String element : boardtxt){
            for (String word : element.split(" ")) {
                if (word.equals("*")){
                    whiteBall.put(word.toString(),Integer.toString(row)+"-"+Integer.toString(column));
                    column++;
                    columncounter++;
                }
                else {
                    coordinates.put(Integer.toString(row)+"-"+Integer.toString(column),word);
                    column++;
                    columncounter++;
                }
            }
            column = 0;
            row++;
            System.out.println(element);
            FileOutput.writeToFile("output.txt",element,true,true);
        }
        Game.gamePlay(coordinates,whiteBall,movetxt[0],row-1,(columncounter/row)-1);
    }
}