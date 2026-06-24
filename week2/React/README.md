# React Lab 1: Getting Started with React

## Objectives

### 1. What is a Single Page Application (SPA) and its Benefits?
A **Single Page Application (SPA)** is a web application or website that interacts with the user by dynamically rewriting the current web page with new data from the web server, instead of the default method of a browser loading entire new pages.
- **Benefits**:
  - **Faster Transitions**: No need to reload the entire page, only the content that changes is updated.
  - **Better User Experience (UX)**: Smooth transitions and a feel similar to a desktop/mobile app.
  - **Reduced Server Load**: The server only needs to send data (usually JSON/XML) instead of fully rendered HTML documents.
  - **Caching Capabilities**: SPAs can cache local data effectively, enabling offline functionality.

---

### 2. Differences Between SPA and MPA
| Feature | Single-Page Application (SPA) | Multi-Page Application (MPA) |
| :--- | :--- | :--- |
| **Page Reloads** | No full page reloads. Only content areas update. | Every user action requests a new page from the server. |
| **Speed/Performance** | Fast after initial load (renders on client-side). | Slow initial load and slower subsequent transitions. |
| **Development** | Clear separation between front-end and back-end. | Front-end and back-end are often tightly coupled. |
| **SEO friendliness** | Harder to optimize (requires SSR or pre-rendering). | Search engine friendly out of the box. |
| **User Experience** | Fluid and highly interactive. | Interrupted by loading spinners and page refreshes. |

---

### 3. Pros & Cons of Single-Page Applications
* **Pros**:
  - Extremely fast and responsive.
  - Easy to build native-like mobile/desktop apps by reusing backend APIs.
  - Smooth animations and transitions are easier to implement.
* **Cons**:
  - Heavy initial load time (requires downloading JS bundle).
  - Search Engine Optimization (SEO) can be complex.
  - Security risks such as Cross-Site Scripting (XSS).
  - Memory leaks can occur since the application page is persistent.

---

### 4. What is React and How It Works?
**React** is an open-source, component-based front-end JavaScript library developed by Facebook for building user interfaces, particularly for single-page applications.
- **How it works**: React allows developers to create reusable UI components. It manages the state of the application and uses a declarative approach. When the state of a component changes, React updates the Virtual DOM and efficiently syncs it with the real DOM using a reconciliation algorithm.

---

### 5. What is the Virtual DOM?
The **Virtual DOM (VDOM)** is a lightweight programming concept where an ideal, or "virtual", representation of a UI is kept in memory and synced with the "real" DOM by a library such as ReactDOM. This process is called **reconciliation**.
- Instead of updating the browser's heavy DOM tree directly for every change, React updates a lightweight virtual tree, compares it with the previous one (diffing), and updates only the changed parts of the real DOM.

---

### 6. Key Features of React
- **JSX (JavaScript XML)**: A syntax extension that allows writing HTML-like code inside JavaScript.
- **Components**: Reusable, independent blocks of code that represent parts of the UI.
- **One-Way Data Binding**: Data flows in a single direction (parent to child via props), making debugging easier.
- **Virtual DOM**: Maximizes rendering performance.
- **State and Props**: Manage internal component data (`state`) and external configuration (`props`).
