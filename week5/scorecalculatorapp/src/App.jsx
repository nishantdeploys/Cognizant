import { useState } from 'react'
import CalculateScore from './components/CalculateScore'

function App() {
  const [name, setName] = useState('')
  const [school, setSchool] = useState('')
  const [total, setTotal] = useState('')
  const [goal, setGoal] = useState('')

  return (
    <main className="container">
      <h1>Student Management Portal</h1>

      <div className="form-card">
        <label htmlFor="name">Name</label>
        <input
          id="name"
          type="text"
          value={name}
          onChange={(event) => setName(event.target.value)}
          placeholder="Enter student name"
        />

        <label htmlFor="school">School</label>
        <input
          id="school"
          type="text"
          value={school}
          onChange={(event) => setSchool(event.target.value)}
          placeholder="Enter school name"
        />

        <label htmlFor="total">Total</label>
        <input
          id="total"
          type="number"
          value={total}
          onChange={(event) => setTotal(event.target.value)}
          placeholder="Enter total score"
        />

        <label htmlFor="goal">Goal</label>
        <input
          id="goal"
          type="number"
          value={goal}
          onChange={(event) => setGoal(event.target.value)}
          placeholder="Enter goal value"
        />
      </div>

      <CalculateScore Name={name} School={school} Total={total} goal={goal} />
    </main>
  )
}

export default App
