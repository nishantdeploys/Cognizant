import './App.css'

const featuredOffice = {
  name: 'Skyline Hub',
  rent: 68000,
  address: '12 MG Road, Bengaluru',
}

const officeSpaces = [
  {
    name: 'Skyline Hub',
    rent: 68000,
    address: '12 MG Road, Bengaluru',
  },
  {
    name: 'Nexus Park',
    rent: 54000,
    address: '45 Anna Salai, Chennai',
  },
  {
    name: 'Harbor View',
    rent: 75000,
    address: '8 Marine Drive, Mumbai',
  },
  {
    name: 'Central Desk',
    rent: 42000,
    address: '100 Park Street, Kolkata',
  },
]

function App() {
  return (
    <main className="page">
      <section className="hero">
        <div className="hero__text">
          <p className="eyebrow">Office Space Rental</p>
          <h1>Find the right workspace for your team</h1>
          <p className="hero__summary">
            A simple React JSX example showing office details, a featured image,
            and a list of rental spaces with dynamic rent color.
          </p>

          <div className="featured-card">
            <h2>Featured Office</h2>
            <p>{featuredOffice.name}</p>
            <p className="rent">Rent: Rs. {featuredOffice.rent}</p>
            <p>Address: {featuredOffice.address}</p>
          </div>
        </div>

        <div className="hero__image-wrap">
          <img
            className="hero__image"
            src="https://images.unsplash.com/photo-1497366754035-f200968a6e72?auto=format&fit=crop&w=900&q=80"
            alt="Modern office space"
          />
        </div>
      </section>

      <section className="office-list">
        <h2>Available Offices</h2>
        <div className="office-grid">
          {officeSpaces.map((office) => (
            <article key={`${office.name}-${office.address}`} className="office-card">
              <h3>{office.name}</h3>
              <p className={office.rent < 60000 ? 'rent rent--low' : 'rent rent--high'}>
                Rent: Rs. {office.rent}
              </p>
              <p>Address: {office.address}</p>
            </article>
          ))}
        </div>
      </section>
    </main>
  )
}

export default App
