/*
 * Copyright (C) 2014 Kasun Amarasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kuz.tmp.old.controller;

import java.io.File;

/**
 *
 * @author Kasun Amarasena
 */
public class FileUtil {

    /**
     * Search for folder or file in the current directory
     *
     * @param name file name
     * @param directory directory to search
     * @return true is folder/file name exists, false otherwise
     */
    public static boolean isFound(String name, String directory) {
        if (name == null || directory == null) {
            return false;
        } else if (name.equals("") || directory.equals("")) {
            return false;
        }
        File dir = new File(directory);
        for (String s : dir.list()) {
            if (s.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Delete a specified file from a directory
     *
     * @param dir
     * @throws java.lang.IllegalAccessException
     */
    public static void deleteFile(File dir) throws IllegalAccessException {

        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                deleteFile(f);
            }
        }
        if (!dir.delete()) {
            throw new IllegalAccessException("Sub dir: not deleted");
        } 
    }
}
