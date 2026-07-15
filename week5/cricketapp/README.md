# Cricket App

## Project Overview
This React app shows cricket player examples using simple ES6 features. It displays either `ListofPlayers` or `IndianPlayers` based on a flag.

## Technologies Used
- React
- JavaScript
- ES6 features
- Create React App

## Folder Structure
- `src/App.js` - Main home page with the flag logic
- `src/ListofPlayers.js` - Uses `map` and `filter`
- `src/IndianPlayers.js` - Uses destructuring and array merge

## How to Run
1. Install dependencies:
   ```bash
   npm install
   ```
2. Start the app:
   ```bash
   npm start
   ```

## Expected Output
When `flag` is `true`, the app shows `ListofPlayers` with all 11 players and the players below 70.

When `flag` is `false`, the app shows `IndianPlayers` with odd team players, even team players, and merged players.
