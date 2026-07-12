import './App.css';

function Home() {
  return <h2>Welcome to the Home page of Student Management Portal</h2>;
}

function About() {
  return <h2>Welcome to the About page of the Student Management Portal</h2>;
}

function Contact() {
  return <h2>Welcome to the Contact page of the Student Management Portal</h2>;
}

function App() {
  return (
    <div className="App">
      <main className="portal-shell">
        <Home />
        <About />
        <Contact />
      </main>
    </div>
  );
}

export default App;
