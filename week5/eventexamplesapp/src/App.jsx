import './App.css'

import { useState } from 'react'
import CurrencyConvertor from './CurrencyConvertor.jsx'

function App() {
  const [counter, setCounter] = useState(0)
  const [message, setMessage] = useState('')

  const sayHello = (text) => {
    setMessage(`Hello ${text}`)
  }

  const increaseCounter = () => {
    setCounter((value) => value + 1)
    sayHello('welcome to React events')
  }

  const decreaseCounter = () => {
    setCounter((value) => value - 1)
  }

  const sayWelcome = (text) => {
    setMessage(`Welcome ${text}`)
  }

  const handlePress = () => {
    setMessage('I was clicked')
  }

  return (
    <main className="page">
      <section className="card intro">
        <p className="eyebrow">Event Examples App</p>
        <h1>HTML form events in React</h1>
        <p>Simple buttons and a currency converter showing basic synthetic event handling.</p>
      </section>

      <section className="card counter-card">
        <h2>Counter</h2>
        <p className="counter-value">{counter}</p>
        <div className="button-row">
          <button type="button" onClick={increaseCounter}>
            Increment
          </button>
          <button type="button" onClick={decreaseCounter}>
            Decrement
          </button>
        </div>
        <button type="button" className="secondary" onClick={() => sayWelcome('welcome')}>
          Say Welcome
        </button>
        <button type="button" className="secondary" onClick={handlePress}>
          OnPress
        </button>
        <p className="message">{message}</p>
      </section>

      <CurrencyConvertor />
    </main>
  )
}

export default App
