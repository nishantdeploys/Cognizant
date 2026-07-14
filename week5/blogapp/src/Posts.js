import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      hasError: false,
      error: null,
    };
  }

  static getDerivedStateFromError() {
    return { hasError: true };
  }

  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then((response) => response.json())
      .then((data) => {
        const posts = data.map((item) => new Post(item.title, item.body));
        this.setState({ posts });
      })
      .catch((error) => {
        this.setState({ error });
      });
  }

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error) {
    alert(`An error occurred: ${error.message}`);
  }

  render() {
    const { posts, error, hasError } = this.state;

    if (hasError) {
      return <p className="message">Something went wrong.</p>;
    }

    if (error) {
      return <p className="message">Unable to load posts.</p>;
    }

    return (
      <div className="posts-list">
        {posts.map((post, index) => (
          <article className="post-card" key={`${post.title}-${index}`}>
            <h2>{post.title}</h2>
            <p>{post.body}</p>
          </article>
        ))}
      </div>
    );
  }
}

export default Posts;
