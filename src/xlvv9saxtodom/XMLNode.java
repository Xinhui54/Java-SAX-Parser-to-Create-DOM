/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlvv9saxtodom;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Xinhui Li
 */
public class XMLNode {
        public String name;
        public String content = "";
        public HashMap<String,ArrayList<XMLNode>> properties = new HashMap<String,ArrayList<XMLNode>>();
        public HashMap<String,String> attributes = new HashMap<String,String>();
}
