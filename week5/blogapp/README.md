# Blog App

## Project Overview
This is a simple React blog assignment that fetches posts from JSONPlaceholder and displays the title and body of each post.

## Technologies Used
- React
- JavaScript
- Fetch API
- Create React App

## Folder Structure
- `src/App.js` - Renders the `Posts` component
- `src/Post.js` - Simple class used to store post data
- `src/Posts.js` - Class component that loads and displays posts

## How to Run
1. Install dependencies:
   ```bash
   npm install
   ```
2. Start the app:
   ```bash
   npm start
   ```

## componentDidMount()
`componentDidMount()` runs once after the component is shown on the page. It is used here to load posts from the API.

## componentDidCatch()
`componentDidCatch()` runs when an error happens in a child component. It is used here to show an alert if any error occurs.

## Expected Output
The page displays a list of blog posts. Each post shows a title as a heading and the body as a paragraph.
