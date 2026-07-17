import { useState } from 'react'

function CurrencyConvertor() {
  const [rupees, setRupees] = useState('')
  const [euro, setEuro] = useState('')

  const handleSubmit = (event) => {
    event.preventDefault()

    const amount = Number.parseFloat(rupees)
    if (Number.isNaN(amount)) {
      setEuro('')
      return
    }

    const convertedValue = amount / 90
    setEuro(convertedValue.toFixed(2))
  }

  return (
    <form className="card converter" onSubmit={handleSubmit}>
      <h2>CurrencyConvertor</h2>
      <label htmlFor="rupees">Indian Rupees</label>
      <input
        id="rupees"
        type="number"
        value={rupees}
        onChange={(event) => setRupees(event.target.value)}
        placeholder="Enter amount in INR"
      />
      <button type="submit">Convert</button>
      <p className="result">Euro: {euro === '' ? '0.00' : euro}</p>
    </form>
  )
}

export default CurrencyConvertor